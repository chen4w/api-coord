package repchain.inter.cooperation.http.async.single.request;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import com.rcjava.sign.impl.ECDSASign;
import com.rcjava.util.CertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.http.model.Header;
import repchain.inter.cooperation.http.model.InterCoResult;
import repchain.inter.cooperation.http.model.tran.Signature;
import repchain.inter.cooperation.http.model.yml.InterCo;
import repchain.inter.cooperation.http.model.yml.RepchainConfig;
import repchain.inter.cooperation.http.model.yml.Service;
import repchain.inter.cooperation.http.utils.ECSignatureUtil;
import repchain.inter.cooperation.http.utils.PkUtil;
import repchain.inter.cooperation.http.utils.YamlUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className Server
 * @date 2021年10月11日 3:03 下午
 * @description 请求方服务端签名接口示例
 */
public class AsyncRequestServer {

    private static final Logger logger = LoggerFactory.getLogger(AsyncRequestServer.class);

    /**
     * @author lhc
     * @description // 服务断启动
     * @date 3:11 下午 2021/10/11
     * @params [args]
     **/
    public static void main(String[] args) {
        // 创建Http服务器，端口为8888
        HttpUtil.createServer(8889)
                .addAction("/callback", AsyncRequestServer::callback)
                .start();
    }

    /**
     * @return void
     * @author lhc
     * @description // 异步请求后的回调接口
     * @date 4:08 下午 2021/10/14
     * @params [httpServerRequest, httpServerResponse]
     **/
    private static void callback(HttpServerRequest request, HttpServerResponse response) {
        InterCoResult result = InterCoResult.builder().build();
        try {
            // 获取请求头
            String headerStr = request.getParam("header");
            Header header = JSONUtil.toBean(headerStr, Header.class);
            // 校验权限
            if (validAuth(header)) {
                // 获取返回的数据
                String data = request.getParam("data");
                System.out.println(data);
                // 根据业务逻辑使用获得的数据...
                // 构建返回结果
                result = result
                        .toBuilder()
                        .code(0)
                        .msg("已收到返回数据")
                        .signature(sign("已收到返回数据"))
                        .build();
            } else {
                // 构建失败返回结果数据
                result = result
                        .toBuilder()
                        .code(1)
                        .msg("no auth")
                        .signature(sign("no auth"))
                        .build();
            }
        } catch (Exception e) {
            // 构建服务器异常返回结果数据
            logger.error("error", e);
            result = result
                    .toBuilder()
                    .code(2)
                    .msg("server error")
                    .signature(sign("server error"))
                    .build();
        } finally {
            // 返回数据给请求方
            response.write(JSONUtil.toJsonStr(result));
        }
    }



    /**
     * @author lhc
     * @description // 数据签名
     * @date 2:15 下午 2021/10/13
     * @params [resultData (返回的数据结果) ]
     **/
    public static Signature sign(Object resultData) {
        // 获取yml文件中的信息
        RepchainConfig repchainConfig = YamlUtils.repchainConfig;
        List<InterCo> interCoList = repchainConfig.getRepchain().getInterCo();
        InterCo interCo = interCoList.get(0);
        // 获取PrivateKey对象
        PrivateKey privateKey = PkUtil.getPrivateKey(interCo.handlePrivateKey(), interCo.getPassword());
        // 将传输内容转换为hash，hash算法需与接口定义一致
        String contentHash = DigestUtil.sha256Hex(JSONUtil.toJsonStr(resultData));
        // 构建签名对象
        return Signature
                .builder()
                // 用户的唯一标识
                .eid(interCo.getCreditCode())
                // 用户证书名称
                .cert_name(interCo.getCertName())
                // 内容hash
                .hash(contentHash)
                // 对内容数据进行签名，现有阶段都通过SHA256withECDSA算法进行签名，后续提供扩展
                .sign(HexUtil.encodeHexStr((new ECDSASign()).sign(privateKey, JSONUtil.toJsonStr(resultData).getBytes(StandardCharsets.UTF_8), "SHA256withECDSA")))
                // 时间戳
                .timeCreate(System.currentTimeMillis())
                .build();
    }

    /**
     * @return boolean
     * @author lhc
     * @description // 校验用户是否拥有调用接口权限
     * @date 3:47 下午 2021/10/18
     * @params [signature, header, requestStr]
     **/
    public static boolean validAuth(Header header) {
        // 获取yml文件中的信息
        RepchainConfig repchainConfig = YamlUtils.repchainConfig;
        List<InterCo> interCoList = repchainConfig.getRepchain().getInterCo();
        InterCo interCo = interCoList.get(0);
        List<Service> services = interCo.getServices();
        Service service = null;
        // 判断yml文件中是否包含调用方id
        for (Service s : services) {
            if (s.getE_from().equals(header.getE_from())) {
                service = s;
            }
        }
        // 若不存在id，则返回false，证明调用方没有调用权限
        if (service == null) {
            return false;
        }
        // 若存在则继续校验签名信息是否正确，先获从yml文件中获取调用方证书信息
        X509Certificate x509Certificate = null;
        try {
            x509Certificate = CertUtil.generateX509Cert(service.handleToCert());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 构建校验对象，进行数据校验
        ECSignatureUtil ecSignatureUtil = new ECSignatureUtil();
        ecSignatureUtil.setSignAlgorithm("SHA256withECDSA");
        byte[] sign = HexUtil.decodeHex(header.getValidStr());
        return ecSignatureUtil.verify(sign, header.getSignData().getBytes(StandardCharsets.UTF_8), x509Certificate);
    }
}
