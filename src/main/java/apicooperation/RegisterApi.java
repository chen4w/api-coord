package apicooperation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import model.ApiDefinition;
import model.ApiServAndAck;

import java.util.UUID;

/**
 * @author zyf
 */
public class RegisterApi {

    private static ApiBaseClient apiRegisterClient = ApiBaseClient.ApiRegisterClient;
    public static ApiBaseClient apireqClient = ApiBaseClient.ApireqClient;
    public static ApiBaseClient apiServClient = ApiBaseClient.ApiServClient;

    private static String apiDefId = "apidef123456789";
    private static String apiVersion = "1";
    public static String serviceId = "serv123456789";
    public static String ackReceiveId = "ack123456789";

    private static TranPostClient postClient = new TranPostClient("localhost:8081");

    public static void main(String[] args) {
        registerApi();
        registerServInstance();
        registerCallBckInstance();
    }

    /**
     * 定义接口
     */
    public static void registerApi() {
        ApiDefinition apiDefinition = new ApiDefinition(apiDefId, 1, apiVersion, "sha-256", "sha1withecdsa", "", "", "");
        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = apiRegisterClient.getTranCreator().createInvokeTran(tranId, apiRegisterClient.getCertId(),
                apiRegisterClient.getApiCooperationId(), "registerApiDefinition", JSON.toJSONString(apiDefinition));
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println(res);
    }

    /**
     * 根据接口定义，定义接口服务实现
     * 服务方来提交交易
     */
    public static void registerServInstance() {
        ApiServAndAck apiServAndAck = new ApiServAndAck(serviceId, apiDefId, apiVersion, apiServClient.getCertId().getCreditCode(), "192.168.2.1", 8080);
        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = apiServClient.getTranCreator().createInvokeTran(tranId, apiServClient.getCertId(),
                apiServClient.getApiCooperationId(), "registerApiService", JSON.toJSONString(apiServAndAck));
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println(res);
    }

    /**
     * 根据接口定义，定义接口应答实现
     * 请求方来提交交易
     */
    public static void registerCallBckInstance() {
        ApiServAndAck apiServAndAck = new ApiServAndAck(ackReceiveId, apiDefId, apiVersion, apireqClient.getCertId().getCreditCode(), "192.168.2.1", 8082);
        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = apireqClient.getTranCreator().createInvokeTran(tranId, apireqClient.getCertId(),
                apireqClient.getApiCooperationId(), "registerApiAckReceive", JSON.toJSONString(apiServAndAck));
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println(res);

    }
}
