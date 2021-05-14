package examples;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import examples.apicoord.*;
import org.apache.commons.codec.binary.Base64;
import com.google.protobuf.ByteString;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import com.rcjava.sign.impl.ECDSASign;
import model.ReqAckProof;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static apicooperation.RegisterApi.*;

public class ApiCoordTools {
    public static void certificate(Header bh, Signature signature,Signature response) {
        TranPostClient postClient = new TranPostClient("192.168.2.69:8081");

        String cert_name1 = signature.getEid().equals("275825005723476137") ? "apiReqSignA" : "apiAckSignA";
        String cert_name2 = signature.getEid().equals("275825005723476137") ? "apiAckSignA" : "apiReqSignA";

        ReqAckProof reqProof = new ReqAckProof(bh.getCid(), bh.getEFrom(), bh.getETo(), bh.getMethod(), bh.getBReq(),
                bh.getBEnd(), bh.getSeq(), signature.getHash().toString(), "", signature.getTmCreate(),
                new ReqAckProof.Signature(signature.getEid(), cert_name1, signature.getSign().toString(),
                        signature.getTmCreate(), signature.getSign().toString()),
                new ReqAckProof.Signature(response.getEid(), cert_name2, response.getHash().toString(),
                        response.getTmCreate(), response.getSign().toString()));
        String tranId = UUID.randomUUID().toString().replace("-", "");
        Peer.Transaction tran = apireqClient.getTranCreator().createInvokeTran(tranId, apireqClient.getCertId(),
                apireqClient.getApiCooperationId(), "reqAckProof", JSON.toJSONString(reqProof));
        JSONObject res = postClient.postSignedTran(tran);
        System.out.println("certificate: " + res);
    }

    public static PrivateKey getPrivateKey(String name, String password, String alias) {
        FileInputStream fis;
        PrivateKey privateKey = null;
        try {
            fis = new FileInputStream(name);
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(fis, password.toCharArray());
            fis.close();
            privateKey = (PrivateKey) keyStore.getKey(alias,password.toCharArray());
            System.out.println("提取的私钥为:" + Base64.encodeBase64String(privateKey.toString().getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static Signature getSignature(String certName, String content, PrivateKey privateKey) {
        return Signature.newBuilder()
                .setEid(certName)
                .setHash(ByteString.copyFrom(DigestUtil.sha256(content)))
                .setTmCreate(System.currentTimeMillis())
                .setSign(ByteString.copyFrom((new ECDSASign()).sign(privateKey, content.getBytes(StandardCharsets.UTF_8), "SHA1withECDSA"))).build();
    }

    public static Header getHeader(Boolean b_req, Boolean b_end, int seq) {
        return Header.newBuilder()
                .setCid("req123456789")
                .setEFrom(ackReceiveId)
                .setETo(serviceId)
                .setMethod("")
                .setBReq(b_req)
                .setBEnd(b_end)
                .setSeq(seq)
                .setTmCreate(System.currentTimeMillis()).build();
    }

    public static ReqRiReSignature getReqRiReSignature(Header bh, ArrayList<String> requestAccounts, Signature signature) {
        Req_ri_re req_ri_re = Req_ri_re.newBuilder().setBh(bh).addAllAccounts(requestAccounts).build();
        return ReqRiReSignature.newBuilder().setReqRiRe(req_ri_re).setSignature(signature).build();
    }

    public static ReqRbSignature getReqRbSignature(Header bh, long tm_from, long tm_to, Signature signature) {
        Req_rb req_rb = Req_rb.newBuilder().setBh(bh).setTmFrom(tm_from).setTmTo(tm_to).build();
        return ReqRbSignature.newBuilder().setReqRb(req_rb).setSignature(signature).build();
    }

    public static HashMap<String, TranRecord> getAccountHistoryArray() {
        TranRec tranRec1 = TranRec.newBuilder().setAccount("account7").setAmount(100).setTm(1).build();
        TranRec tranRec2 = TranRec.newBuilder().setAccount("account8").setAmount(100).setTm(1).build();
        return new HashMap() {{
            put("account1", TranRecord.newBuilder().setAcc("account1").addCredit(tranRec1).addDebit(tranRec2).build());
            put("account2", TranRecord.newBuilder().setAcc("account2").addCredit(tranRec1).addDebit(tranRec2).build());
            put("account3", TranRecord.newBuilder().setAcc("account3").addCredit(tranRec1).addDebit(tranRec2).build());
            put("account4", TranRecord.newBuilder().setAcc("account4").addCredit(tranRec1).addDebit(tranRec2).build());
            put("account5", TranRecord.newBuilder().setAcc("account5").addCredit(tranRec1).addDebit(tranRec2).build());
            put("account6", TranRecord.newBuilder().setAcc("account6").addCredit(tranRec1).addDebit(tranRec2).build());}};
    }

    public static ResultSignature getResultSignature(Header bh, ArrayList<TranRecord> ts, Signature signature) {
        Result result = Result.newBuilder().setBh(bh).addAllTs(ts).build();
        System.out.println("getResultSignature: " + signature.toString());
        return ResultSignature.newBuilder().setResult(result).setSignature(signature).build();
    }

    public static ResultSignature2 getResultSignature2(Header bh, ArrayList<TranRecord> ts, Signature signature, Signature signature2) {
        Result result = Result.newBuilder().setBh(bh).addAllTs(ts).build();
        return ResultSignature2.newBuilder().setResult(result).setSignature(signature).setSignature2(signature2).build();
    }

    public static ArrayList<TranRecord> getAccountHistory(ArrayList<String> requestAccounts, HashMap<String, TranRecord> AccountHistoryArray) {
        ArrayList<TranRecord> AccountHistory = new ArrayList<>();
        for (String requestAccount : requestAccounts)
            AccountHistory.add(AccountHistoryArray.get(requestAccount));
        return AccountHistory;
    }
}
