package apicooperation;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static apicooperation.GenerateJksCert.*;

/**
 * @author zyf
 */
public class SignUpSignerCert {

    private static NodeBaseClient node1Client = NodeBaseClient.Node1Client;
    private static String directory = "api/certs/";

    private static String approverName = "account";
    private static String apiRegisterName = "apiRegister";
    private static String apiReqName = "apiRegister";
    private static String apiAckName = "apiRegister";

    private static Peer.ChaincodeId contractCertId = Peer.ChaincodeId.newBuilder().setChaincodeName("ContractCert").setVersion(1).build();
    private static TranPostClient postClient = new TranPostClient(("localhost:8081"));

    public static void main(String[] args) throws IOException {
        signUpSigner(approverName, accountApproverId, Arrays.asList(accountApproverSslName, accountApproverSignName));
        signUpSigner(apiRegisterName, apiRegisterId, Arrays.asList(apiRegisterSslName, apiRegisterSignName));
        signUpSigner(apiReqName, apiReqId, Arrays.asList(apiReqSslName, apiReqSignName));
        signUpSigner(apiAckName, apiAckId, Arrays.asList(apiAckSslName, apiAckSignName));
        signUpCert(node1Client, accountApproverId, accountApproverSignName);
        signUpCert(node1Client, apiRegisterId, apiRegisterSignName);
        signUpCert(node1Client, apiReqId, apiReqSignName);
        signUpCert(node1Client, apiAckId, apiAckSignName);
    }

    /**
     * 注册账户
     *
     * @param signerName
     * @param userCreditCode
     * @param userCertNames
     * @throws InvalidProtocolBufferException
     */
    public static void signUpSigner(String signerName, String userCreditCode, List userCertNames) throws InvalidProtocolBufferException {
        Peer.Signer signer = Peer.Signer.newBuilder().setName(signerName).setCreditCode(userCreditCode).setMobile("13812345678").addAllCertNames(userCertNames).build();

        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = node1Client.getTranCreator().createInvokeTran(tranId, node1Client.getCertId(), contractCertId, "SignUpSigner", JsonFormat.printer().print(signer));
        // 提交交易
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println(res);
    }

    /**
     * 注册证书
     *
     * @param client         NodeBaseClient或者是ApiBaseClient
     * @param userCreditCode
     * @param userCertName
     * @throws IOException
     */
    public static <T> void signUpCert(T client, String userCreditCode, String userCertName) throws IOException {

        //注册用户证书
        String pemCer = Files.asCharSource(new File(String.format(directory + "%s.%s.cer", userCreditCode, userCertName)), Charsets.UTF_8).read();

        //注册时间
        long regMillis = System.currentTimeMillis() + 8 * 3600 * 1000;
        // 注销时间
        long unRegMillis = System.currentTimeMillis() + 2 * 8 * 3600 * 1000;
        Timestamp regTime = Timestamp.newBuilder().setSeconds(regMillis / 1000).setNanos((int) (regMillis % 1000) * 1000000).build();
        Timestamp unRegTime = Timestamp.newBuilder().setSeconds(unRegMillis / 1000).setNanos((int) (unRegMillis % 1000) * 1000000).build();
        // 也可以自己构造一个javaBean，然后使用Json序列化
        Peer.Certificate certificate = Peer.Certificate.newBuilder()
                .setCertificate(pemCer)
                .setAlgType("SHA1withECDSA")
                .setCertValid(true)
                .setRegTime(regTime)
                .setUnregTime(unRegTime)
                .build();

        JSONObject certInfo = new JSONObject();
        certInfo.fluentPut("credit_code", userCreditCode);
        certInfo.fluentPut("name", userCertName);
        certInfo.fluentPut("cert", JSONObject.parse(JsonFormat.printer().print(certificate)));

        String tranId = UUID.randomUUID().toString().replace("-", "");

        Peer.Transaction signUpCer = null;

        if (client instanceof NodeBaseClient) {
            signUpCer = ((NodeBaseClient) client).getTranCreator().createInvokeTran(tranId, ((NodeBaseClient) client).getCertId(), contractCertId, "SignUpCert", certInfo.toJSONString());
        } else if (client instanceof ApiBaseClient) {
            signUpCer = ((ApiBaseClient) client).getTranCreator().createInvokeTran(tranId, ((ApiBaseClient) client).getCertId(), contractCertId, "SignUpCert", certInfo.toJSONString());
        } else {

        }

        String signUpCerStr = Hex.encodeHexString(signUpCer.toByteArray());
        JSONObject signUpCerResult = postClient.postSignedTran(JSON.toJSONString(signUpCerStr));
        System.out.println(signUpCerResult);

    }

}
