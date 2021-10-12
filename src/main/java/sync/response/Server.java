package sync.response;

import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import model.ReqAckProof;
import utils.ResponseAck;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className Server
 * @date 2021年10月11日 3:03 下午
 * @description 同步单次请求服务端代码
 */
public class Server {

    /**
     * @author lhc
     * @description // 服务断启动
     * @date 3:11 下午 2021/10/11
     * @params [args]
     * @return void
     **/
    public static void main(String[] args) {
        // 创建简易服务器，端口为8888
        HttpUtil.createServer(8888)
                .addAction("/info", Server::getInfo)
                .start();
    }

    /**
     * @author lhc
     * @description // 模拟controller方法
     * @date 2:17 下午 2021/10/12
     * @params [name]
     * @return void
     **/
    public static void getInfo(HttpServerRequest request, HttpServerResponse response) {
        // 服务端获取请求，构建服务方存证对象
        ResponseAck responseAck = new ResponseAck();
        // 获取请求时存证
        responseAck.cb(new ReqAckProof());
        // 构建返回结果数据
        String name = request.getParam("name");
        String info = "This is your info," + name;
        Map<String,Object> result = new HashMap<>(3);
        result.put("code", 0);
        result.put("msg", "success");
        result.put("data", info);
        // 返回结果存证
        responseAck.ci(new ReqAckProof());
        // 返回数据给请求方
        response.write(JSONUtil.toJsonStr(result));
        // 返回数据结束存证
        responseAck.ce(new ReqAckProof());
    }
}
