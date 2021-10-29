package repchain.inter.cooperation.middleware.service;


/**
 * @author lhc
 * @version 1.0
 * @className CommunicationServer
 * @date 2021年10月27日 9:24 上午
 * @description 描述
 */
public interface CommunicationServer {

    /**
     * @author lhc
     * @description // 启动中间件接收其他中间件数据的服务
     * @date 2021/10/29 5:32 下午
     * @params []
     * @return void
     **/
    void start();

    /**
     * @author lhc
     * @description // 设置接收其他中间件数据后，进行数据转发的客户端
     * @date 2021/10/29 5:33 下午
     * @params [receiveClient]
     * @return void
     **/
    void setReceiveClient(ReceiveClient receiveClient);

}
