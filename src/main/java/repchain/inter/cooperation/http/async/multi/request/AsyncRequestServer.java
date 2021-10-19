package repchain.inter.cooperation.http.async.multi.request;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import com.rcjava.sign.impl.ECDSASign;
import repchain.inter.cooperation.http.model.tran.Signature;
import repchain.inter.cooperation.http.model.yml.InterCo;
import repchain.inter.cooperation.http.model.yml.RepchainConfig;
import repchain.inter.cooperation.http.utils.PkUtil;
import repchain.inter.cooperation.http.utils.YamlUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className Server
 * @date 2021年10月11日 3:03 下午
 * @description 请求方服务端签名接口示例
 */
public class AsyncRequestServer {

    /**
     * @author lhc
     * @description // 服务断启动
     * @date 3:11 下午 2021/10/11
     * @params [args]
     **/
    public static void main(String[] args) {
        // 创建Http服务器，端口为8888
        HttpUtil.createServer(8889)
                .addAction("/callback",AsyncRequestServer::callback)
                // 签名接口，对调用方数据进行数据签名
                .addAction("/sign", AsyncRequestServer::sign)
                .start();
    }

    /**
     * @author lhc
     * @description // 异步请求后的回调接口
     * @date 4:08 下午 2021/10/14
     * @params [httpServerRequest, httpServerResponse]
     * @return void
     **/
    private static void callback(HttpServerRequest request, HttpServerResponse response) {

    }


    /**
     * @author lhc
     * @description // 数据签名
     * @date 2:15 下午 2021/10/13
     * @params [request, response]
     **/
    public static void sign(HttpServerRequest request, HttpServerResponse response) {
        // 获取yml文件中的信息
        RepchainConfig repchainConfig = YamlUtils.repchainConfig;
        List<InterCo> interCoList = repchainConfig.getRepchain().getInterCo();
        InterCo interCo = interCoList.get(0);
        // 获取PrivateKey对象
        PrivateKey privateKey = PkUtil.getPrivateKey(interCo.handlePrivateKey(),interCo.getPassword());
        // 传过来为json字符串
        String data = request.getParam("data");
        // 将传输内容转换为hash，hash算法需与接口定义一致
        String contentHash = DigestUtil.sha256Hex(data);
        // 构建签名对象
        Signature signature = Signature
                .builder()
                // 用户的唯一标识
                .eid(interCo.getCreditCode())
                // 用户证书名称
                .cert_name(interCo.getCertName())
                // 内容hash
                .hash(contentHash)
                // 对内容数据进行签名
                .sign(HexUtil.encodeHexStr((new ECDSASign()).sign(privateKey, data.getBytes(StandardCharsets.UTF_8), "SHA256withECDSA")))
                // 时间戳
                .timeCreate(System.currentTimeMillis())
                .build();
        // 返回签名数据
        response.write(JSONUtil.toJsonStr(signature));
    }
}
