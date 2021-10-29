package repchain.inter.cooperation.middleware.service.impl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import repchain.inter.cooperation.middleware.pool.grpc.ComClientPool;
import repchain.inter.cooperation.middleware.pool.grpc.ComClientSingle;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.proto.TransformGrpc;
import repchain.inter.cooperation.middleware.service.CommunicationClient;

import java.util.concurrent.TimeUnit;

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
        String host = "localhost";
        int port = 50051;
        ComClientSingle comClientSingle = ComClientPool.borrowObject(host, port);
        Result result = comClientSingle.sendMessage(transEntity);
        ComClientPool.returnObject(comClientSingle, host, port);
        return result;
    }
}
