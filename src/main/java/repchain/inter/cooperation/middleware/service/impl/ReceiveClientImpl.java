package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.http.HttpUtil;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.service.ReceiveClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className ReceiveClientImpl
 * @date 2021年10月27日 9:53 上午
 * @description 描述
 */
public class ReceiveClientImpl implements ReceiveClient {

    @Override
    public Result msg(TransEntity transEntity) {
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", transEntity.getHeader().getData());
        String result = HttpUtil.get("http://42.193.105.24//user/sgUser/valid",map);
        return Result.newBuilder().setData(result).setMsg("Action OK").build();
    }
}
