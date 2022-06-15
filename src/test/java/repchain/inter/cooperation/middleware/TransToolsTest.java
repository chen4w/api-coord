package repchain.inter.cooperation.middleware;

import org.junit.jupiter.api.Test;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.model.tran.Signature;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.utils.PkUtil;
import repchain.inter.cooperation.middleware.utils.TransTools;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

import java.security.PrivateKey;

/**
 * @author lhc
 * @version 1.0
 * @className TransTools
 * @date 2021年11月03日 10:05 上午
 * @description 描述
 */
public class TransToolsTest {

    @Test
    public void AuthTest() {
        RepChain repchain = YamlUtils.getRepchain();
        String contentHash = "a381d9f8df7d70016b29781e258a18c4ec6669875976a204715d29587566184d";
        PrivateKey privateKey = PkUtil.getPrivateKey(repchain.handlePrivateKey(), repchain.getPassword());
        Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(), repchain.getCertName(), "sha256withecdsa");
        Header header = Header.builder().validStr(contentHash).signData(signature.getSign()).build();
//        TransTools.validAuth();
    }

    @Test
    public void validAuth(){
//        TransTools.validAuth("");
    }
}
