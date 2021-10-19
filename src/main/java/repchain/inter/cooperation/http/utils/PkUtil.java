package repchain.inter.cooperation.http.utils;

import com.rcjava.util.KeyUtil;
import repchain.inter.cooperation.http.model.SysCert;
import repchain.inter.cooperation.http.model.yml.InterCo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCSException;

import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;

/**
 * @author lhc
 * @version 1.0
 * @className PkUtil
 * @date 2021年10月13日 1:54 下午
 * @description 描述
 */
public class PkUtil {

    public static PrivateKey getPrivateKey(String privateKeyPem, String password) {
        PEMParser parser = new PEMParser(new StringReader(privateKeyPem));
        PrivateKey privateKey = null;
        try {
            privateKey = KeyUtil.generatePrivateKey(parser, false, password);
        } catch (IOException | OperatorCreationException | PKCSException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static SysCert getSysCert(InterCo interCo) {
        return SysCert
                .builder()
                .certName(interCo.getCertName())
                .creditCode(interCo.getCreditCode())
                .password(interCo.getPassword())
                // 注意此处需要用handleCert方法获取证书内容
                .cert(interCo.handleCert())
                // 注意此处需要用handlePrivateKey方法获取私钥
                .privateKey(interCo.handlePrivateKey())
                .build();
    }
}
