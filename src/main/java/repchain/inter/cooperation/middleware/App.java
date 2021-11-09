package repchain.inter.cooperation.middleware;

import repchain.inter.cooperation.middleware.builder.RpMiddleware;
import repchain.inter.cooperation.middleware.service.impl.*;


/**
 * @author lhc
 * @version 1.0
 * @className App
 * @date 2021年10月27日 9:08 上午
 * @description 打包成jar时提供启动的启动类
 */
public class App {


    /**
     * @return void
     * @author lhc
     * @description // 构建中间件建造者，启动中间件
     * @date 2021/10/29 5:25 下午
     * @params [args]
     **/
    public static void main(String[] args) {
        RpMiddleware middleware = RpMiddleware.builder()
                .persistence(new PersistenceImpl())
                .authFilter(new AuthFilterImpl())
                .transactionCommit(new TransactionCommitImpl())
                .communicationClient(new CommunicationClientImpl())
                .receiveServer(new ReceiveServerImpl())
                .receiveClient(new ReceiveClientImpl())
                .communicationServer(new CommunicationServerImpl())
                .blockSync(new BlockSyncImpl())
                .build();
        middleware.start();
    }
}
