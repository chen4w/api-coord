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

    Result msg(TransEntity transEntity);

}
