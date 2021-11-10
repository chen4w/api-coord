package repchain.inter.cooperation.middleware.service;

import repchain.inter.cooperation.middleware.model.tran.ApiDefinition;
import repchain.inter.cooperation.middleware.model.tran.ApiServAndAck;

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

    Object msg(String serviceId, int seq, boolean isEnd,
               String url, boolean bReqFlag, String method,
               String callbackMethod, String callbackUrl, String cid,
               boolean sync, Map<String, Object> map);

    Object file(String serviceId, int seq, boolean isEnd,
                String url, boolean bReqFlag, String method,
                String callbackMethod, String callbackUrl, String cid,
                boolean sync,String filePath,String fileHash, Map<String, Object> map);

    void setTransactionCommit(TransactionCommit commit);

    void setPersistence(Persistence persistence);
}
