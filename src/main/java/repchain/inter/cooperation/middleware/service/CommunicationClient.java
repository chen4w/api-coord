package repchain.inter.cooperation.middleware.service;

import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;

/**
 * @author lhc
 * @version 1.0
 * @className CommunicationClient
 * @date 2021年10月27日 9:30 上午
 * @description 描述
 */
public interface CommunicationClient {

    /**
     * @author `lhc`
     * @description // 中间件发送数据给中间件
     * @date 2021/10/29 5:31 下午
     * @params [transEntity]
     * @return repchain.inter.cooperation.middleware.proto.Result
     **/
    Result sendMessage(TransEntity transEntity);
}
