package repchain.inter.cooperation.middleware.service;

import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;

/**
 * @author lhc
 * @version 1.0
 * @className ReceiveClient
 * @date 2021年10月27日 9:31 上午
 * @description 描述
 */
public interface ReceiveClient {

    /**
     * @author lhc
     * @description // 接收到数据进行服务转发
     * @date 2021/10/29 5:34 下午
     * @params [transEntity]
     * @return repchain.inter.cooperation.middleware.proto.Result
     **/
    Result msg(TransEntity transEntity);

    void setAuthFilter(AuthFilter authFilter);
}
