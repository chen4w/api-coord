package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.MiddleConstant;
import repchain.inter.cooperation.middleware.proto.Header;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.service.CommunicationClient;
import repchain.inter.cooperation.middleware.service.ReceiveServer;

import java.util.Map;
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
                .addAction("/msg", (req, res) -> {
                    try {
                        String id = req.getParam("id");
                        int seq = Integer.parseInt(req.getParam("seq"));
                        Integer isEnd = Integer.parseInt(req.getParam("isEnd"));
                        String url= req.getParam("url");
                        boolean endFlag;
                        if (isEnd == MiddleConstant.NOT_END) {
                            endFlag = false;
                        } else {
                            endFlag = true;
                        }
                        String data = req.getParam("data");
                        Map<String, Object> map = JSONUtil.parseObj(data);
                        res.setContentType("text/html;charset=utf-8");
                        Object result = msg(id, seq, endFlag, map);
                        String resultStr;
                        if (result instanceof String) {
                            resultStr = (String) result;
                        } else {
                            resultStr = JSONUtil.toJsonStr(result);
                        }
                        res.write(resultStr);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        res.write(e.getMessage());
                    }
                })
                .addAction("/file", this::file)
                .start();
        logger.info("Http Server started, listening on " + port);
    }

    @Override
    public Object msg(String id, int seq, boolean isEnd, Map<String, Object> map) {
        TransEntity transEntity = TransEntity.newBuilder().setHeader(Header.newBuilder().setData(JSONUtil.toJsonStr(map)).build()).build();
        Result result = communicationClient.sendMessage(transEntity, id);
        return result.getData();
    }

    @Override
    public void file(HttpServerRequest request, HttpServerResponse response) {

    }
}
