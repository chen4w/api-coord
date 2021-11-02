package repchain.inter.cooperation.middleware.builder;

import repchain.inter.cooperation.middleware.service.*;

/**
 * @author lhc
 * @version 1.0
 * @className RpMiddlewareBuilder
 * @date 2021年10月27日 9:12 上午
 * @description 中间件建造者，用于建造集成中间件各个模块组件
 */
public class RpMiddleware {


    private AuthFilter authFilter;
    private BlockSync blockSync;
    private CommunicationClient communicationClient;
    private CommunicationServer communicationServer;
    private ReceiveClient receiveClient;
    private ReceiveServer receiveServer;
    private TransactionCommit transactionCommit;

    public RpMiddleware(AuthFilter authFilter, BlockSync blockSync, CommunicationClient communicationClient,
                        CommunicationServer communicationServer, ReceiveClient receiveClient,
                        ReceiveServer receiveServer, TransactionCommit transactionCommit) {
        this.authFilter = authFilter;
        this.blockSync = blockSync;
        this.communicationClient = communicationClient;
        this.communicationServer = communicationServer;
        this.receiveClient = receiveClient;
        this.receiveServer = receiveServer;
        this.transactionCommit = transactionCommit;
    }

    public static RpMiddlewareBuilder builder() {
        return new RpMiddlewareBuilder();
    }

    public RpMiddleware toBuilder() {
        return (new RpMiddlewareBuilder())
                .authFilter(this.authFilter)
                .blockSync(this.blockSync)
                .communicationClient(this.communicationClient)
                .communicationServer(this.communicationServer)
                .receiveClient(this.receiveClient)
                .receiveServer(this.receiveServer)
                .transactionCommit(transactionCommit)
                .build();
    }

    public void start(){
        System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
        if (this.receiveServer == null) {
            throw new NullPointerException("receiveServer can not be null!!!");
        }
        if (this.communicationServer == null) {
            throw new NullPointerException("communicationServer can not be null!!!");
        }
        this.receiveServer.start();
        this.communicationServer.start();
        this.blockSync.start();
    }

    public static class RpMiddlewareBuilder {
        private AuthFilter authFilter;
        private BlockSync blockSync;
        private CommunicationClient communicationClient;
        private CommunicationServer communicationServer;
        private ReceiveClient receiveClient;
        private ReceiveServer receiveServer;
        private TransactionCommit transactionCommit;

        RpMiddlewareBuilder() {

        }

        public RpMiddlewareBuilder authFilter(AuthFilter authFilter) {
            this.authFilter = authFilter;
            return this;
        }

        public RpMiddlewareBuilder blockSync(BlockSync blockSync) {
            this.blockSync = blockSync;
            return this;
        }

        public RpMiddlewareBuilder communicationClient(CommunicationClient communicationClient) {
            this.communicationClient = communicationClient;
            return this;
        }

        public RpMiddlewareBuilder communicationServer(CommunicationServer communicationServer) {
            this.communicationServer = communicationServer;
            this.communicationServer.setReceiveClient(this.receiveClient);
            return this;
        }

        public RpMiddlewareBuilder receiveClient(ReceiveClient receiveClient) {
            this.receiveClient = receiveClient;
            this.receiveClient.setAuthFilter(this.authFilter);
            return this;
        }

        public RpMiddlewareBuilder receiveServer(ReceiveServer receiveServer) {
            this.receiveServer = receiveServer;
            this.receiveServer.setCommunicationClient(this.communicationClient);
            this.receiveServer.setTransactionCommit(this.transactionCommit);
            return this;
        }

        public RpMiddlewareBuilder transactionCommit(TransactionCommit transactionCommit) {
            this.transactionCommit = transactionCommit;
            return this;
        }

        public RpMiddleware build() {
            return new RpMiddleware(this.authFilter, this.blockSync, this.communicationClient,
                    this.communicationServer, this.receiveClient, this.receiveServer, this.transactionCommit);
        }
    }
}
