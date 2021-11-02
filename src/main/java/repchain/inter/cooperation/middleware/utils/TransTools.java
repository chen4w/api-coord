package repchain.inter.cooperation.middleware.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import com.rcjava.sign.impl.ECDSASign;
import com.rcjava.util.CertUtil;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;
import repchain.inter.cooperation.middleware.model.tran.Signature;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * @author lhc
 * @version 1.0
 * @className TransTools
 * @date 2021年10月29日 10:46 上午
 * @description 描述
 */
public class TransTools {

    public static Signature getSignature(PrivateKey privateKey, String contentHash, String creditCode, String certName, String alg) {
        // 获取PrivateKey对象
        // 构建签名对象
        return Signature
                .builder()
                // 用户的唯一标识
                .eid(creditCode)
                // 用户证书名称
                .cert_name(certName)
                // 内容hash
                .hash(contentHash)
                // 对内容hash数据进行签名
                .sign(HexUtil.encodeHexStr((new ECDSASign()).sign(privateKey, contentHash.getBytes(StandardCharsets.UTF_8), alg)))
                // 时间戳
                .timeCreate(System.currentTimeMillis())
                .build();
    }

    public static ReqAckProof getReqAckProof(Header header, String contentHash, Signature signature, Signature toBean) {
        // 构建提交给区块链的交易对象
        ReqAckProof reqAckProof = ReqAckProof
                .builder()
                // 将内容进行hash取值，hash算法请遵循服务定义的内容hash算法，默认为SHA256withECDSA
                .hash(contentHash)
                // 设置请求方签名对象
                .sign_r(signature)
                // 设置服务方签名对象
                .sign_c(toBean)
                .build();
        // 将header请求头中内容copy到reqAckProof中，构建交易对象
        BeanUtil.copyProperties(header, reqAckProof);
        return reqAckProof;
    }

    /**
     * @return boolean
     * @author lhc
     * @description // 校验用户是否拥有调用接口权限
     * @date 3:47 下午 2021/10/18
     * @params [signature, header, requestStr]
     **/
    public static boolean validAuth(Header header, String alg, boolean isReq) {
        // 获取yml文件中的信息
        String cert;
        if (isReq) {
            cert = YamlUtils.getFromCert(header.getE_from());
        } else {
            cert = YamlUtils.getToCert(header.getE_to());
        }
        if (StrUtil.isEmpty(cert)) {
            return false;
        }
        // 若存在则继续校验签名信息是否正确，先获从yml文件中获取调用方证书信息
        X509Certificate x509Certificate = null;
        try {
            x509Certificate = CertUtil.generateX509Cert(cert);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 构建校验对象，进行数据校验
        ECSignatureUtil ecSignatureUtil = new ECSignatureUtil();
        ecSignatureUtil.setSignAlgorithm(alg);
        byte[] sign = HexUtil.decodeHex(header.getValidStr());
        return ecSignatureUtil.verify(sign, header.getSignData().getBytes(StandardCharsets.UTF_8), x509Certificate);
    }
}
