package repchain.inter.cooperation.middleware.service;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;

import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className HttpServer
 * @date 2021年10月27日 9:23 上午
 * @description 描述
 */
public interface ReceiveServer {

    void setCommunicationClient(CommunicationClient communicationClient);

    void start();

    Object msg(String serviceId,int seq,boolean isEnd, Map<String,Object> map);

    void file(HttpServerRequest request, HttpServerResponse response);

    void setTransactionCommit(TransactionCommit commit);
}
