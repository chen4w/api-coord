package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.exception.ServiceException;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.model.tran.ApiDefinition;
import repchain.inter.cooperation.middleware.model.tran.ApiServAndAck;
import repchain.inter.cooperation.middleware.model.tran.Signature;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.ResultFile;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.proto.TransFile;
import repchain.inter.cooperation.middleware.service.AuthFilter;
import repchain.inter.cooperation.middleware.service.ReceiveClient;
import repchain.inter.cooperation.middleware.utils.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PrivateKey;
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
    OutputStream os = null;

    private ReceiveClient receiveClient;
    private TransFile file;
    private String filePath;
    private AuthFilter authFilter = new AuthFilterImpl();
    private PrivateKey privateKey;
    private RepChain repchain;

    public FileServerObserver(StreamObserver<Result> responseObserver, ReceiveClient receiveClient) {
        this.responseObserver = responseObserver;
        this.receiveClient = receiveClient;
        this.repchain = YamlUtils.getRepchain();
        this.privateKey = PkUtil.getPrivateKey(repchain.handlePrivateKey(), repchain.getPassword());
    }

    @Override
    public void onNext(TransFile fileInfo) {
        this.file = fileInfo;
        Header header = JSONUtil.toBean(this.file.getHeader(), Header.class);
        ApiServAndAck from = MyCacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, header.getE_from(), ApiServAndAck.class);
        if (from == null) {
            throw new ServiceException("无法在区块链找到对应的接口登记或接口调用");
        } else {
            ApiDefinition apiDefinition = MyCacheManager.getValue(EhCacheConstant.API_DEFINITION, from.getD_id(), ApiDefinition.class);
            if (authFilter.validAuth(header, apiDefinition.getAlgo_sign(), header.getB_req())) {
                try {
                    if (!fileInfo.getBegin()) {
                        if (os == null) {
                            File file = new File("./tmp/file");
                            if (!file.exists()) {
                                boolean flag = file.mkdirs();
                                if (flag) {
                                    throw new ServiceException("创建文件夹失败！");
                                }
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
            } else {
                throw new ServiceException("无权限");
            }
        }

    }

    @Override
    public void onError(Throwable t) {
        logger.error("接收文件异常", t);
        String msg =
        String contentHash = DigestUtil.sha256Hex("接收方接收文件异常：" + t.getMessage());
        Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(), repchain.getCertName(), "sha256withecdsa");
        Result result = Result.newBuilder().setCode(2).setMsg(msg).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
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
        if (this.file.getBegin()) {

        } else if (!GetFileSHA256.getFileSha256(tempFile).equals(this.file.getSha256())) {
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
