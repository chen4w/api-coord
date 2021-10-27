package repchain.inter.cooperation.middleware;

import repchain.inter.cooperation.middleware.builder.RpMiddleware;
import repchain.inter.cooperation.middleware.service.impl.CommunicationClientImpl;
import repchain.inter.cooperation.middleware.service.impl.CommunicationServerImpl;
import repchain.inter.cooperation.middleware.service.impl.ReceiveClientImpl;
import repchain.inter.cooperation.middleware.service.impl.ReceiveServerImpl;

/**
 * @author lhc
 * @version 1.0
 * @className App
 * @date 2021年10月27日 9:08 上午
 * @description 打包成jar时提供启动的启动类
 */
public class App {

    public static void main(String[] args) {
        RpMiddleware middleware = RpMiddleware.builder()
                .communicationClient(new CommunicationClientImpl())
                .receiveServer(new ReceiveServerImpl())
                .receiveClient(new ReceiveClientImpl())
                .communicationServer(new CommunicationServerImpl())
                .build();
        middleware.start();
    }
}
