package repchain.inter.cooperation.middleware.utils;

import cn.hutool.core.util.HexUtil;
import com.rcjava.sign.impl.ECDSASign;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.model.tran.Signature;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

/**
 * @author lhc
 * @version 1.0
 * @className TransTools
 * @date 2021年10月29日 10:46 上午
 * @description 描述
 */
public class TransTools {

//    /**
//     * @return model.Header
//     * @author lhc
//     * @description // 构建中间接口存证请求头
//     * @date 5:22 下午 2021/10/13
//     * @params [service (yml文件读取，包含请求方id和调用方id), cid (请求id), seq (存证序号), isEnd （是否为结束调用存证）]
//     **/
//    public static Header customHeader(Service service, String cid, boolean isEnd, int seq, Signature signature) {
//        return Header
//                .builder()
//                // 请求Id
//                .cid(cid)
//                // 请求方id，可从yml文件中获取，此id为服务请求登记时的id，可从dashboard中查看
//                .e_from(service.getE_from())
//                .e_to(service.getE_to())
//                // 请求接口/方法
//                .method("GET http://" + service.getTo_host() + ":" + service.getTo_port() + "/info")
//                // 创建时间
//                .tm_create(System.currentTimeMillis())
//                // 请求 or 应答标志, true 代表请求; false 代表应答
//                .b_req(true)
//                // 结束标志, true 代表结束（即本次请求/应答为最后一个）,false代表未结束
//                .b_end(isEnd)
//                // 请求或应答的序号, 从1开始
//                .seq(1)
//                // 用于校验权限签名的字符串
//                .validStr(signature.getSign())
//                // 签名的数据
//                .signData(signature.getHash())
//                .build();
//
//    }
//
//    /**
//     * @return model.tran.Signature
//     * @author lhc
//     * @description // 构建签名对象
//     * @date 4:41 下午 2021/10/13
//     * @params [interCo （读取yml文件内容）, contentHash （内容存证的hash）]
//     **/
//    public static Signature getSignature(InterCo interCo, String contentHash) {
//        // 获取PrivateKey对象
//        PrivateKey privateKey = PkUtil.getPrivateKey(interCo.handlePrivateKey(), interCo.getPassword());
//        // 构建签名对象
//        return Signature
//                .builder()
//                // 用户的唯一标识
//                .eid(interCo.getCreditCode())
//                // 用户证书名称
//                .cert_name(interCo.getCertName())
//                // 内容hash
//                .hash(contentHash)
//                // 对内容hash数据进行签名
//                .sign(HexUtil.encodeHexStr((new ECDSASign()).sign(privateKey, contentHash.getBytes(StandardCharsets.UTF_8), "SHA256withECDSA")))
//                // 时间戳
//                .timeCreate(System.currentTimeMillis())
//                .build();
//    }
}
