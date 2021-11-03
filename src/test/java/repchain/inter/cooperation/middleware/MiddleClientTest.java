package repchain.inter.cooperation.middleware;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import repchain.inter.cooperation.middleware.client.HttpType;
import repchain.inter.cooperation.middleware.client.MiddlewareClient;
import repchain.inter.cooperation.middleware.client.ReqOption;
import repchain.inter.cooperation.middleware.model.InterCoResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className MiddleClientTest
 * @date 2021年11月03日 11:29 上午
 * @description 描述
 */
public class MiddleClientTest {

    @Test()
    public void msg() {
        // 创建客户端
        MiddlewareClient client = new MiddlewareClient("http://localhost:8888", 50000);
        // 构建请求参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("loginName", "12110107bi45jh675g");
        // 发送请求，并获取返回结果
        InterCoResult result = client.msg("1", "/user/sgUser/valid", HttpType.GET, map);

        System.out.println(JSONUtil.toJsonPrettyStr(result));
        System.out.println(result.getData());
    }

    @Test()
    public void multi() {
        MiddlewareClient client = new MiddlewareClient("http://localhost:8888", 50000);
        Map<String, Object> map = new HashMap<>(1);
        ReqOption reqOption = new ReqOption();
        reqOption.setIsEnd(ReqOption.FALSE);
        map.put("loginName", "12110107bi45jh675g");
        InterCoResult result = client.msg("1", "/user/sgUser/valid", HttpType.GET, map, reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(reqOption));
        System.out.println(JSONUtil.toJsonPrettyStr(result));
        reqOption = new ReqOption();
        reqOption.setCid(result.getCid());
        reqOption.setSeq(2);
        reqOption.setIsEnd(ReqOption.FALSE);
        map = new HashMap<>(1);
        map.put("loginName", "yewu");
        result = client.msg("1", "/user/sgUser/valid", HttpType.GET, map, reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(reqOption));
        System.out.println(JSONUtil.toJsonPrettyStr(result));
        reqOption = new ReqOption();
        reqOption.setCid(result.getCid());
        reqOption.setSeq(3);
        reqOption.setIsEnd(ReqOption.FALSE);
        map = new HashMap<>(1);
        map.put("loginName", "test");
        result = client.msg("1", "/user/sgUser/valid", HttpType.GET, map, reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(reqOption));
        System.out.println(JSONUtil.toJsonPrettyStr(result));
        reqOption = new ReqOption();
        reqOption.setCid(result.getCid());
        reqOption.setSeq(4);
        map = new HashMap<>(1);
        map.put("loginName", "pro_user");
        result = client.msg("1", "/user/sgUser/valid", HttpType.GET, map, reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(reqOption));
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }
}
