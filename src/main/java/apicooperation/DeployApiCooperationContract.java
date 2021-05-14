package apicooperation;


import com.alibaba.fastjson.JSONObject;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import com.rcjava.tran.impl.DeployTran;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author zyf
 */
public class DeployApiCooperationContract {

    // 使用管理员来部署接口协同合约
    private static NodeBaseClient client = NodeBaseClient.Node1Client;

    public static void main(String[] args) throws IOException {

        Peer.ChaincodeId customTplId = Peer.ChaincodeId.newBuilder().setChaincodeName("InterfaceCooperation").setVersion(1).build();
        String tplString = Files.readString(Path.of("E:\\gztest\\repchain\\src\\main\\scala\\rep\\sc\\tpl\\api", "InterfaceCooperation.scala"), StandardCharsets.UTF_8);

        DeployTran deployTran = DeployTran.newBuilder()
                .setTxid(DigestUtils.sha256Hex(tplString))
                .setCertId(client.getCertId())
                .setChaincodeId(customTplId)
                .setSpcPackage(tplString)
                .setLegal_prose("")
                .setTimeout(5000)
                .setCodeType(Peer.ChaincodeDeploy.CodeType.CODE_SCALA)
                .build();
        Peer.Transaction signedDeployTran = client.getTranCreator().createDeployTran(deployTran);

        JSONObject deployRes = new TranPostClient("localhost:8081").postSignedTran(signedDeployTran);

        System.out.println(deployRes);
    }
}
