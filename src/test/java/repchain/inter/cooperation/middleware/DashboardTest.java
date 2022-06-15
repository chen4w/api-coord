package repchain.inter.cooperation.middleware;

import com.alibaba.fastjson.JSONObject;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import com.rcjava.tran.TranCreator;
import com.rcjava.tran.impl.DeployTran;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.service.impl.ReceiveClientImpl;
import repchain.inter.cooperation.middleware.utils.PkUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lhc
 * @version 1.0
 * @className DashboardTest
 * @date 2022年06月07日 14:34
 * @description 描述
 */
public class DashboardTest {

    private static final Logger logger = LoggerFactory.getLogger(DashboardTest.class);

    public static final String PrivateKey = "-----BEGIN ENCRYPTED PRIVATE KEY-----\n" +
            "MIHUMD8GCSqGSIb3DQEFDTAyMBoGCSqGSIb3DQEFDDANBAhQZs1mNXedkwIBZDAU\n" +
            "BggqhkiG9w0DBwQIsKT1qz7ftXYEgZArx0yq98UC2OwMCst5BiGICIIJ++8LGNnO\n" +
            "Ie/IhpirChsNum52HmAtsl2+f2rfspw5raOeslbNPplbLoq1rNAuh7qGiBd0dhxX\n" +
            "JPdDw0UYmIG2Ug+UGUw4wpi/8NvVMpymu98JJj/Yybs5bi+8iY95YHcNXa1cW3D/\n" +
            "tdNcc82RJMRQ1vTt59IcmHI1MwvZZkA=\n" +
            "-----END ENCRYPTED PRIVATE KEY-----";

    private static final String testPrivateKey = "-----BEGIN ENCRYPTED PRIVATE KEY-----\n" +
            "MIHUMD8GCSqGSIb3DQEFDTAyMBoGCSqGSIb3DQEFDDANBAhmCExqklIBYAIBZDAU\n" +
            "BggqhkiG9w0DBwQIqBfUuNLPl9EEgZAJ66W8gCePnUwkEoCwxt6TH3IpwNH5gD7H\n" +
            "ojKJ4x+TrbgZErPaiyQlPuY+/qSQd/SdXSBO1fVRmGQiLB+ITKLfsnq82zT+hHT1\n" +
            "R7+HMeVGo+74c7fgHso1xUlHDUectlGZPH7gVOzPRsHQ6jaHu0PZlbZGHjL+JZwZ\n" +
            "m3xgZLjynagixPN8vZ2x1/DXWn3MfG4=\n" +
            "-----END ENCRYPTED PRIVATE KEY-----";
    TranPostClient postCredenceClient = new TranPostClient("192.168.1.209:9086");

    /**
     * @author lhc
     * @description // 发布测试合约
     * @date 2022/6/7 15:27
     * @params []
     * @return void
     **/
    @Test
    public void deployTpl()  {
        Peer.ChaincodeId customTplId = Peer.ChaincodeId.newBuilder().setChaincodeName("CredenceTPL").setVersion(1).build();
        String tplString = null;
        try {
            tplString = FileUtils.readFileToString(new File("jks/did/tpl/CredenceTPL.scala"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Peer.CertId usr0_certId_0 = Peer.CertId.newBuilder().setCreditCode("951002007l78123233").setCertName("super_admin").build();
        Peer.ChaincodeDeploy chaincodeDeploy = Peer.ChaincodeDeploy.newBuilder()
                .setTimeout(5000)
                .setCodePackage(tplString)
                .setLegalProse("")
                .setCType(Peer.ChaincodeDeploy.CodeType.CODE_SCALA)
                .setRType(Peer.ChaincodeDeploy.RunType.RUN_SERIAL)
                .setSType(Peer.ChaincodeDeploy.StateType.STATE_BLOCK)
                .setInitParameter("")
                .setCclassification(Peer.ChaincodeDeploy.ContractClassification.CONTRACT_CUSTOM)
                .build();
        DeployTran deployTran = DeployTran.newBuilder()
                .setTxid(UUID.randomUUID().toString())
                .setCertId(usr0_certId_0)
                .setChaincodeId(customTplId)
                .setChaincodeDeploy(chaincodeDeploy)
                .build();
        deployTran = deployTran.toBuilder().setTxid(UUID.randomUUID().toString()).build();
        TranCreator tranCreator = TranCreator.newBuilder()
                .setSignAlgorithm("SHA256withECDSA")
                .setPrivateKey(PkUtil.getPrivateKey(PrivateKey, "123456"))
                .build();
        Peer.Transaction signedDeployTran = tranCreator.createDeployTran(deployTran);
        JSONObject jsonObject = postCredenceClient.postSignedTran(signedDeployTran);
        logger.info(jsonObject.toJSONString());
    }

    /**
     * @author lhc
     * @description // 调用测试合约
     * @date 2022/6/7 15:28
     * @params []
     * @return void
     **/
    @Test
    public void creProof(){
        Peer.CertId  testCertId = Peer.CertId.newBuilder().setCreditCode("mD8x0RhuV8").setCertName("test").build();
        Peer.ChaincodeId credenceTPLId = Peer.ChaincodeId.newBuilder().setChaincodeName("CredenceTPL").setVersion(1).build();
        String tranId = UUID.randomUUID().toString();
        TranCreator tranCreator = TranCreator.newBuilder()
                .setSignAlgorithm("SHA256withECDSA")
                .setPrivateKey(PkUtil.getPrivateKey(testPrivateKey, "123456"))
                .build();
        Peer.Transaction tran = tranCreator.createInvokeTran(tranId, testCertId, credenceTPLId,
                "creProof", String.format("{\"uuid\" : \"%s\",\"data\" : \"{\\\"data1\\\": \\\"xyb002\\\",\\\"data2\\\": \\\"xyb003\\\"}\"}", tranId), 0, "");
        String tranHex = Hex.encodeHexString(tran.toByteArray());
        JSONObject jsonObject = postCredenceClient.postSignedTran(tranHex);
        logger.info(jsonObject.toJSONString());
    }
}
