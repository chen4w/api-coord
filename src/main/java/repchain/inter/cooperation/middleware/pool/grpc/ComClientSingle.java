package repchain.inter.cooperation.middleware.pool.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.proto.TransformGrpc;
import repchain.inter.cooperation.middleware.service.impl.CommunicationServerImpl;

import java.util.concurrent.TimeUnit;

/**
 * @author lhc
 * @version 1.0
 * @className ComClientSingle
 * @date 2021年10月29日 3:26 下午
 * @description 描述
 */
public class ComClientSingle {
    private static final Logger logger = LoggerFactory.getLogger(ComClientSingle.class);

    private final ManagedChannel channel;

    public ComClientSingle(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }

    public Result sendMessage(TransEntity transEntity) {
        Result result;
        try {
            TransformGrpc.TransformBlockingStub blockingStub = TransformGrpc.newBlockingStub(channel);
            result = blockingStub.send(transEntity);
        } catch (StatusRuntimeException e) {
            String msg = "RPC调用失败: " + e.getMessage();
            logger.error(msg, e);
            result = Result.newBuilder().setMsg("RPC调用失败: " + e.getMessage()).setCode(2).build();
        }
        return result;
    }

    public void shutdown() throws InterruptedException {
        logger.debug("关闭grpc连接...");
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
