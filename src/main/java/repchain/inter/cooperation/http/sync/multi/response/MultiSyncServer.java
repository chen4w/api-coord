package repchain.inter.cooperation.http.sync.multi.response;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import com.rcjava.sign.impl.ECDSASign;
import com.rcjava.util.CertUtil;
import lombok.extern.slf4j.Slf4j;
import repchain.inter.cooperation.http.model.Header;
import repchain.inter.cooperation.http.model.InterCoResult;
import repchain.inter.cooperation.http.model.tran.Signature;
import repchain.inter.cooperation.http.model.yml.InterCo;
import repchain.inter.cooperation.http.model.yml.RepchainConfig;
import repchain.inter.cooperation.http.model.yml.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.http.utils.ECSignatureUtil;
import repchain.inter.cooperation.http.utils.PkUtil;
import repchain.inter.cooperation.http.utils.YamlUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
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
@Slf4j
public class MultiSyncServer {

    private static final Logger logger = LoggerFactory.getLogger(MultiSyncServer.class);

    /**
     * 模拟数据库列表
     */
    private static final List<Map<Object, Object>> list = new ArrayList<>();

    static {
        // 插入数据，模拟数据库数据
        Map<Object, Object> firstInfo = MapUtil.of(new String[][]{
                {"name", "Tom"},
                {"age", "18"},
        });
        list.add(firstInfo);
        Map<Object, Object> secondInfo = MapUtil.of(new String[][]{
                {"name", "Jack"},
                {"age", "16"},
        });
        list.add(secondInfo);
        Map<Object, Object> thirdInfo = MapUtil.of(new String[][]{
                {"name", "Bin"},
                {"age", "17"},
        });
        list.add(thirdInfo);
        Map<Object, Object> fourthInfo = MapUtil.of(new String[][]{
                {"name", "Lucy"},
                {"age", "18"},
        });
        list.add(fourthInfo);
    }

    /**
     * @author lhc
     * @description // 服务断启动
     * @date 3:11 下午 2021/10/11
     * @params [args]
     **/
    public static void main(String[] args) {
        // 创建Http服务器，端口为8888
        HttpUtil.createServer(8888)
                // 业务接口地址，此处为需要提供给其他系统调用的业务接口 ,地址为/info
                .addAction("/infoList", MultiSyncServer::getInfoList)
                .start();
    }

    /**
     * @author lhc
     * @description // 模拟controller方法，此处为模拟的业务接口
     * @date 2:17 下午 2021/10/12
     * @params [name]
     **/
    public static void getInfoList(HttpServerRequest request, HttpServerResponse response) {
        InterCoResult result = new InterCoResult();
        try {
            // 1. 获取request请求信息
            // 获取调用方请求头header信息
            String headerStr = request.getParam("header");
            Header header = JSONUtil.toBean(headerStr, Header.class);
            // 获取业务请求数据
            int pageNo = Integer.parseInt(request.getParam("pageNo"));
            int pageSize = Integer.parseInt(request.getParam("pageSize"));
            // 2.校验请求方权限，校验请求方数据签名
            if (validAuth(header)) {
                // 3.构建成功返回结果数据
                // 模拟数据库分页查询
                List<Map<Object, Object>> resultList = new ArrayList<>();
                int currIdx = (pageNo > 1 ? (pageNo - 1) * pageSize : 0);
                for (int i = 0; i < pageSize && i < list.size() - currIdx; i++) {
                    resultList.add(list.get(currIdx + i));
                }
                // 构建返回结果对象
                result = result
                        .toBuilder()
                        .code(0)
                        .msg("success")
                        .data(resultList)
                        .signature(sign(resultList))
                        .build();
            } else {
                // 3.构建失败返回结果数据
                result = result
                        .toBuilder()
                        .code(1)
                        .msg("no auth")
                        .signature(sign("no auth"))
                        .build();
            }
        } catch (Exception e) {
            logger.error("error", e);
            // 3.服务器异常返回错误结果
            result = result
                    .toBuilder()
                    .code(2)
                    .msg("server error")
                    .signature(sign("server error"))
                    .build();
        } finally {
            // 4. 返回数据给请求方
            response.write(JSONUtil.toJsonStr(result));
        }
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
        X509Certificate x509Certificate;
        boolean result = false;
        try {
            x509Certificate = CertUtil.generateX509Cert(service.handleCert());
            // 构建校验对象，进行数据校验
            ECSignatureUtil ecSignatureUtil = new ECSignatureUtil();
            ecSignatureUtil.setSignAlgorithm("SHA256withECDSA");
            byte[] sign = HexUtil.decodeHex(header.getValidStr());
            assert x509Certificate != null;
            result = ecSignatureUtil.verify(sign, header.getSignData().getBytes(StandardCharsets.UTF_8), x509Certificate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
                .sign(HexUtil.encodeHexStr((new ECDSASign()).sign(privateKey, contentHash.getBytes(StandardCharsets.UTF_8), "SHA256withECDSA")))
                // 时间戳
                .timeCreate(System.currentTimeMillis())
                .build();
    }
}
