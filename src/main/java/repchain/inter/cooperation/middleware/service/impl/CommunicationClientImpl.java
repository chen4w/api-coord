package repchain.inter.cooperation.middleware.service.impl;

import repchain.inter.cooperation.middleware.pool.grpc.ComClientPool;
import repchain.inter.cooperation.middleware.pool.grpc.ComClientSingle;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.ResultFile;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.proto.TransFile;
import repchain.inter.cooperation.middleware.service.CommunicationClient;

import java.io.File;


/**
 * @author lhc
 * @version 1.0
 * @className CommunicationClientImpl
 * @date 2021年10月27日 9:52 上午
 * @description 描述
 */
public class CommunicationClientImpl implements CommunicationClient {


    @Override
    public Result sendMessage(TransEntity transEntity) {
        String host = transEntity.getHost();
        int port = transEntity.getPort();
        ComClientSingle comClientSingle = ComClientPool.borrowObject(host, port);
        Result result = comClientSingle.sendMessage(transEntity);
        ComClientPool.returnObject(comClientSingle, host, port);
        return result;
    }

    @Override
    public Result sendFile(TransFile transFile, File file) {
        String host = transFile.getHost();
        int port = transFile.getPort();
        ComClientSingle comClientSingle = ComClientPool.borrowObject(host, port);
        Result result = comClientSingle.sendFile(transFile,file);
        ComClientPool.returnObject(comClientSingle, host, port);
        return result;
    }

    @Override
    public ResultFile downloadFile(TransEntity transEntity) {
        String host = transEntity.getHost();
        int port = transEntity.getPort();
        ComClientSingle comClientSingle = ComClientPool.borrowObject(host, port);
        ResultFile result = comClientSingle.download(transEntity);
        ComClientPool.returnObject(comClientSingle, host, port);
        return result;
    }
}
