package repchain.inter.cooperation.middleware.pool.grpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.exception.ServiceException;
import repchain.inter.cooperation.middleware.proto.*;
import repchain.inter.cooperation.middleware.utils.SnowIdGenerator;

import java.io.*;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * @author lhc
 * @version 1.0
 * @className ComClientSingle
 * @date 2021年10月29日 3:26 下午
 * @description Grpc客户端
 */
public class ComClientSingle {
    private static final Logger logger = LoggerFactory.getLogger(ComClientSingle.class);

    // 通道
    private final ManagedChannel channel;

    public ComClientSingle(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }

    /**
     * @return repchain.inter.cooperation.middleware.proto.Result
     * @author lhc
     * @description // 发送消息
     * @date 2021/10/29 5:26 下午
     * @params [transEntity]
     **/
    public Result sendMessage(TransEntity transEntity) {
        Result result;
        try {
            TransformGrpc.TransformBlockingStub blockingStub = TransformGrpc.newBlockingStub(channel);
            result = blockingStub.send(transEntity);
        } catch (StatusRuntimeException e) {
            String msg = "无法调用远程中间件: " + e.getMessage();
            logger.error(msg, e);
            result = Result.newBuilder().setMsg(msg).setCode(2).build();
        }
        return result;
    }

    /**
     * @return repchain.inter.cooperation.middleware.proto.Result
     * @author lhc
     * @description // 通过grpc流，发送文件
     * @date 2021/11/9 4:41 下午
     * @params [transFile, file]
     **/
    public Result sendFile(TransFile transFile, File file) {
        final Result[] result = new Result[1];
        try {
            TransformGrpc.TransformStub stub = TransformGrpc.newStub(channel);
            final CountDownLatch finishLatch = new CountDownLatch(1);
            StreamObserver<Result> responseObserver = new StreamObserver<Result>() {
                @Override
                public void onNext(Result info) {
                    result[0] = info;
                    logger.info("end :" + info.getMsg());
                }

                @Override
                public void onError(Throwable t) {
                    finishLatch.countDown();
                }

                @Override
                public void onCompleted() {
                    try {
                        logger.info("文件传输完成，文件信息:\n" + JsonFormat.printer().print(transFile));
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                    }
                    finishLatch.countDown();
                }
            };
            StreamObserver<TransFile> requestObserver = stub.sendFile(responseObserver);
            if (transFile.getBegin()) {
                requestObserver.onNext(transFile.toBuilder().build());
            } else {
                FileInputStream is = new FileInputStream(file);
                byte[] buff = new byte[2048];
                int len;
                while ((len = is.read(buff)) != -1) {
                    requestObserver.onNext(transFile.toBuilder().setFile(ByteString.copyFrom(buff, 0, len)).build());
                }
            }
            requestObserver.onCompleted();
//            if(finishLatch.await(1, TimeUnit.MINUTES)){
//                throw new ServiceException("中间件传输超时");
//            }
            finishLatch.await(1, TimeUnit.MINUTES);
            logger.info("sendFile success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException("文件传输出错：" + e.getMessage());
        }
        return result[0];
    }

    /**
     * @author lhc
     * @description // grpc请求下载文件
     * @date 2021/11/11 4:17 下午
     * @params [transEntity]
     * @return repchain.inter.cooperation.middleware.proto.ResultFile
     **/
    public ResultFile download(TransEntity transEntity) {
        ResultFile functionResult = null;
        File dir = new File("./tmp/download");
        String filepath = "";
        if (!dir.exists()) {
            boolean flag = dir.mkdirs();
            if (!flag) {
                throw new ServiceException("无法创建下载文件夹");
            }
        }
        OutputStream os = null;
        TransformGrpc.TransformBlockingStub blockingStub = TransformGrpc.newBlockingStub(channel);
        Iterator<ResultFile> result = blockingStub.download(transEntity);
        while (result.hasNext()) {
            ResultFile file = result.next();
            try {
                if (file.getBegin()) {
                    functionResult = file;
                    filepath = "./tmp/download/" + SnowIdGenerator.getId();
                    os = new FileOutputStream(filepath);
                } else {
                    file.getFile().newInput();
                    file.writeTo(os);
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                throw new ServiceException("文件下载出错：" + e.getMessage());
            }
        }
        if (functionResult == null) {
            throw new ServiceException("文件下载出错");
        }
        return functionResult.toBuilder().setFilepath(filepath).build();
    }

    /**
     * @return void
     * @author lhc
     * @description // 关闭grpc通道
     * @date 2021/10/29 5:26 下午
     * @params []
     **/
    public void shutdown() throws InterruptedException {
        logger.debug("关闭grpc连接...");
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
