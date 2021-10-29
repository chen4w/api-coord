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
    Result sendMessage(TransEntity transEntity);
}
