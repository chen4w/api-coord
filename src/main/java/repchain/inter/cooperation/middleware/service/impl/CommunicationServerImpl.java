package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.thread.ExecutorBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.model.yml.MiddleServer;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.proto.TransformGrpc;
import repchain.inter.cooperation.middleware.service.CommunicationServer;
import repchain.inter.cooperation.middleware.service.ReceiveClient;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lhc
 * @version 1.0
 * @className CommunicationServerImpl
 * @date 2021年10月27日 9:52 上午
 * @description 描述
 */
public class CommunicationServerImpl implements CommunicationServer {

    private ReceiveClient receiveClient;

    private static final Logger logger = LoggerFactory.getLogger(CommunicationServerImpl.class);

    private Server server;

    @Override
    public void start() {
        MiddleServer  comServer= YamlUtils.middleConfig.getMiddleware().getComServer();
        int port = comServer.getPort();
        // 创建线程池
        ExecutorService executor = ExecutorBuilder.create()
                .setCorePoolSize(comServer.getCorePoolSize())
                .setMaxPoolSize(comServer.getMaxPoolSize())
                .setWorkQueue(new LinkedBlockingQueue<>(comServer.getWorkQueue()))
                .build();
        try {
            server = ServerBuilder.forPort(port)
                    .addService(new TransformImpl())
                    .executor(executor)
                    .build()
                    .start();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("Grpc Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                CommunicationServerImpl.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    @Override
    public void setReceiveClient(ReceiveClient receiveClient) {
        this.receiveClient = receiveClient;
    }


    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    class TransformImpl extends TransformGrpc.TransformImplBase {
        @Override
        public void send(TransEntity request, StreamObserver<Result> responseObserver){
            Result result = receiveClient.msg(request);
            responseObserver.onNext(result);
            responseObserver.onCompleted();
        }
    }
}
