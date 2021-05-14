package apicooperation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import model.UserRoleEntity;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static apicooperation.GenerateJksCert.*;

/**
 * @author zyf
 */
public class SignUpUserEntity {

    private static NodeBaseClient node1Client = NodeBaseClient.Node1Client;
    private static ApiBaseClient approverClient = ApiBaseClient.ApproverClient;
    private static Peer.ChaincodeId apiContractId = Peer.ChaincodeId.newBuilder().setChaincodeName("InterfaceCooperation").setVersion(1).build();
    private static TranPostClient postClient = new TranPostClient("localhost:8081");


    private static String directory = "api/certs/";

    private static String ApproverRoleType = "0010000";
    private static String ApiRegisterRoleType = "0001000";
    private static String ApiServRoleType = "0000100";
    private static String ApiReqRoleType = "0000010";

    private static UserRoleEntity approver = new UserRoleEntity(accountApproverId, ApproverRoleType);
    private static UserRoleEntity apiRegister = new UserRoleEntity(apiRegisterId, ApiRegisterRoleType);
    private static UserRoleEntity apiServ = new UserRoleEntity(apiAckId, ApiServRoleType);
    private static UserRoleEntity apireq = new UserRoleEntity(apiReqId, ApiReqRoleType);

    public static void main(String[] args) throws IOException {
        signUpAccountApprover();
        signUpApiRegister();
        signUpReq();
        signUpServ();
    }

    /**
     * 管理员来注册账户认证者
     */
    public static void signUpAccountApprover() throws IOException {
        approver.setCert_ssl_name(accountApproverSslName);
        approver.setCert_sign_name(accountApproverSignName);
        setPem(approver);
        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = node1Client.getTranCreator().createInvokeTran(tranId, node1Client.getCertId(), apiContractId, "signUpUserEntity", JSON.toJSONString(approver));
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println(res);
    }

    /**
     * 账户认证者来注册接口定义者
     */
    public static void signUpApiRegister() throws IOException {
        apiRegister.setCert_ssl_name(apiRegisterSslName);
        apiRegister.setCert_sign_name(apiRegisterSignName);
        setPem(apiRegister);
        postTran(approverClient, apiRegister);
    }

    /**
     * 账户认证者来注册接口请求者
     */
    public static void signUpReq() throws IOException {
        apireq.setCert_ssl_name(apiReqSslName);
        apireq.setCert_sign_name(apiReqSignName);
        setPem(apireq);
        postTran(approverClient, apireq);
    }

    /**
     * 账户认证者来注册服务者
     */
    public static void signUpServ() throws IOException {
        apiServ.setCert_ssl_name(apiAckSslName);
        apiServ.setCert_sign_name(apiAckSignName);
        setPem(apiServ);
        postTran(approverClient, apiServ);

    }

    /**
     * @param userRoleEntity
     * @throws IOException
     */
    private static void setPem(UserRoleEntity userRoleEntity) throws IOException {
        String ssl_pem = Files.asCharSource(new File(String.format(directory + "%s.%s.cer", userRoleEntity.getCredit_code(), userRoleEntity.getCert_ssl_name())), Charsets.UTF_8).read();
        String sign_pem = Files.asCharSource(new File(String.format(directory + "%s.%s.cer", userRoleEntity.getCredit_code(), userRoleEntity.getCert_sign_name())), Charsets.UTF_8).read();
        userRoleEntity.setCert_ssl_pem(ssl_pem);
        userRoleEntity.setCert_sign_pem(sign_pem);
    }

    /**
     * @param approverClient
     * @param userRoleEntity
     */
    private static void postTran(ApiBaseClient approverClient, UserRoleEntity userRoleEntity) {
        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = approverClient.getTranCreator().createInvokeTran(tranId, approverClient.getCertId(),
                approverClient.getApiCooperationId(), "signUpUserEntity", JSON.toJSONString(userRoleEntity));
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println(res);
    }

}
