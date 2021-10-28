package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.proto.Header;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.service.CommunicationClient;
import repchain.inter.cooperation.middleware.service.ReceiveServer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;


/**
 * @author lhc
 * @version 1.0
 * @className ReceiveServerImpl
 * @date 2021年10月27日 9:53 上午
 * @description 描述
 */
public class ReceiveServerImpl implements ReceiveServer {

    private static final Logger logger = LoggerFactory.getLogger(CommunicationServerImpl.class);

    public CommunicationClient communicationClient;


    @Override
    public void setCommunicationClient(CommunicationClient communicationClient) {
        this.communicationClient = communicationClient;
    }

    @Override
    public void start() {
        int port = 8888;
        // 创建线程池
        ExecutorService executor = ExecutorBuilder.create()
                .setCorePoolSize(5)
                .setMaxPoolSize(10)
                .setWorkQueue(new LinkedBlockingQueue<>(100))
                .build();
        // 创建http服务
        HttpUtil.createServer(port)
                .setExecutor(executor)
                .addAction("/msg", this::msg)
                .addAction("/file", this::file)
                .start();
        logger.info("Http Server started, listening on " + port);
    }

    @Override
    public void msg(HttpServerRequest request, HttpServerResponse response) {
        String data = request.getParam("data");
        TransEntity transEntity = TransEntity.newBuilder().setHeader(Header.newBuilder().setData(data).build()).build();
        Result result = communicationClient.sendMessage(transEntity);
        System.out.println(result.getData());
        response.setContentType("text/html;charset=utf-8");
        response.write(result.getData());
    }

    @Override
    public void file(HttpServerRequest request, HttpServerResponse response) {

    }
}
