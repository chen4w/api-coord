package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.json.JSONUtil;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.proto.TransFile;
import repchain.inter.cooperation.middleware.service.ReceiveClient;
import repchain.inter.cooperation.middleware.utils.GetFileSHA256;
import repchain.inter.cooperation.middleware.utils.SnowIdGenerator;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * @author lhc
 * @version 1.0
 * @className FileServerObserver
 * @date 2021年11月09日 5:39 下午
 * @description 描述
 */
public class FileServerObserver implements StreamObserver<TransFile> {

    private static final Logger logger = LoggerFactory.getLogger(FileServerObserver.class);


    StreamObserver<Result> responseObserver;
    final long startTime = System.nanoTime();
    OutputStream os = null;

    URL url = null;
    HttpURLConnection con = null;
    private ReceiveClient receiveClient;
    private TransFile file;
    private String filePath;

    public FileServerObserver(StreamObserver<Result> responseObserver, ReceiveClient receiveClient) {
        this.responseObserver = responseObserver;
        this.receiveClient = receiveClient;
    }

    @Override
    public void onNext(TransFile fileInfo) {
        this.file = fileInfo;
        try {
            if (fileInfo.getBegin()) {

            } else {
                if (os == null) {
                    File file = new File("./tmp/file");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    this.filePath = "./tmp/file/" + SnowIdGenerator.getId();
                    os = new FileOutputStream(this.filePath);
                }
                fileInfo.getFile().newInput();
                fileInfo.getFile().writeTo(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable t) {
        logger.error("接收文件异常", t);
    }

    @Override
    public void onCompleted() {
        // 关闭流
        try {
            if (os != null) {
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        File tempFile = new File(this.filePath);
        if (!GetFileSHA256.getFileSha256(tempFile).equals(this.file.getSha256())) {
            Result result = Result.newBuilder().setCode(2).setMsg("文件sha256不一致，请重新传输文件").build();
            responseObserver.onNext(result);
        } else {
            Header header = JSONUtil.toBean(this.file.getHeader(), Header.class);
            Map<String, Object> map = JSONUtil.parseObj(header.getData());
            map.put("filePath", this.filePath);
            map.put("fileName", this.file.getFileName());
            header.setData(JSONUtil.toJsonStr(map));
            TransEntity transEntity = TransEntity
                    .newBuilder()
                    .setPort(this.file.getPort())
                    .setHost(this.file.getHost())
                    .setHeader(JSONUtil.toJsonStr(header))
                    .build();
            Result result = this.receiveClient.msg(transEntity);
            responseObserver.onNext(result);
        }
        responseObserver.onCompleted();
    }
}
