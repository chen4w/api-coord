package repchain.inter.cooperation.middleware.service;

import java.sql.SQLException;
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
               boolean sync, boolean reqSaveFlag, boolean resultSaveFlag, Map<String, Object> headers, String callbackId, Map<String, Object> map) throws SQLException;

    Object file(String serviceId, int seq, boolean isEnd,
                String url, boolean bReqFlag, String method,
                String callbackMethod, String callbackUrl, String cid,
                boolean sync, String filePath, String fileHash, boolean reqSaveFlag,
                boolean resultSaveFlag, boolean fileSaveFlag, Map<String, Object> headers, String fileField, Map<String, Object> map) throws SQLException;

    void setTransactionCommit(TransactionCommit commit);

    void setPersistence(Persistence persistence);
}
