package apicooperation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import model.ReqAckProof;

import java.util.UUID;

import static apicooperation.ApiBaseClient.ApiServClient;
import static apicooperation.ApiBaseClient.ApireqClient;
import static apicooperation.RegisterApi.*;

/**
 * @author zyf
 */
public class ReqAckProofTest {

    private static TranPostClient postClient = new TranPostClient("localhost:8081");

    public static void main(String[] args) {
        reqProof();
        ackProof();
    }

    public static void reqProof() {
        ReqAckProof reqProof = new ReqAckProof("req123456789", ackReceiveId, serviceId, "", true, false, 1, "hash123", "hash456", System.currentTimeMillis(),
                new ReqAckProof.Signature(ApireqClient.getCertId().getCreditCode(), "apiReqSignA", "hash", System.currentTimeMillis(), "sign"),
                new ReqAckProof.Signature(ApiServClient.getCertId().getCreditCode(), "apiAckSignA", "hash", System.currentTimeMillis(), "sign"));

        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = apireqClient.getTranCreator().createInvokeTran(tranId, apireqClient.getCertId(),
                apireqClient.getApiCooperationId(), "reqAckProof", JSON.toJSONString(reqProof));
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println(res);
    }

    public static void ackProof() {
        ReqAckProof ackProof = new ReqAckProof("req123456789", ackReceiveId, serviceId, "", false, false, 2, "hash123", "hash456", System.currentTimeMillis(),
                new ReqAckProof.Signature(ApireqClient.getCertId().getCreditCode(), "apiReqSignA", "hash", System.currentTimeMillis(), "sign"),
                new ReqAckProof.Signature(ApiServClient.getCertId().getCreditCode(), "apiAckSignA", "hash", System.currentTimeMillis(), "sign"));

        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = apiServClient.getTranCreator().createInvokeTran(tranId, apiServClient.getCertId(),
                apiServClient.getApiCooperationId(), "reqAckProof", JSON.toJSONString(ackProof));
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println(res);
    }
}
