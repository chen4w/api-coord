package repchain.inter.cooperation.http.async.multi.response;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import com.rcjava.sign.impl.ECDSASign;
import repchain.inter.cooperation.http.model.Header;
import repchain.inter.cooperation.http.model.tran.Signature;
import repchain.inter.cooperation.http.model.yml.InterCo;
import repchain.inter.cooperation.http.model.yml.RepchainConfig;
import repchain.inter.cooperation.http.utils.PkUtil;
import repchain.inter.cooperation.http.utils.YamlUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className Server
 * @date 2021年10月11日 3:03 下午
 * @description 同步单次请求服务端代码
 */
public class AsyncServer {

    /**
     * @return void
     * @author lhc
     * @description // 服务断启动
     * @date 3:11 下午 2021/10/11
     * @params [args]
     **/
    public static void main(String[] args) {
        // 创建Http服务器，端口为8888
        HttpUtil.createServer(8888)
                // 业务接口地址，此处为需要提供给其他系统调用的业务接口 ,地址为/info
                .addAction("/info", AsyncServer::getInfo)
                .start();
    }

    /**
     * @return void
     * @author lhc
     * @description // 模拟controller方法，此处为模拟的业务接口
     * @date 2:17 下午 2021/10/12
     * @params [name]
     **/
    public static void getInfo(HttpServerRequest request, HttpServerResponse response) {
        // 获取请求头
        String headerStr = request.getParam("header");
        Header header = JSONUtil.toBean(headerStr, Header.class);
        // 将header信息存入数据库或其他持久化地方，方便异步返回数据时调用
        int id = 0;
        try {
            id = Db.use().insert(Entity.create("header").parseBean(header));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 构建返回结果数据
        String name = request.getParam("name");
        // 将请求参数存入数据库，方便异步返回数据
        try {
            Db.use().insert(Entity.create("header").set("yewu_name", name).set("header_id", id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Map<String, Object> result = new HashMap<>(2);
        result.put("code", 0);
        result.put("msg", "数据已收到，待审核通过后返回请求数据！");
        // 返回数据给请求方
        response.write(JSONUtil.toJsonStr(result));
    }
}
