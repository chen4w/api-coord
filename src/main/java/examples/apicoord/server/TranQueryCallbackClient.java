package examples.apicoord.server;

import com.google.protobuf.ByteString;
import examples.ApiCoordTools;
import examples.apicoord.*;
import io.grpc.Channel;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import static apicooperation.ApiBaseClient.ApiServClient;

public class TranQueryCallbackClient {
    private static final Logger logger = Logger.getLogger(TranQueryCallbackClient.class.getName());
    private final TranQueryCallbackGrpc.TranQueryCallbackBlockingStub blockingStub;
    public TranQueryCallbackClient(Channel channel) {
        blockingStub = TranQueryCallbackGrpc.newBlockingStub(channel);
    }

    private int requestAccountsIndex = 0;
    private int seq = 1;
    // 存储所有请求和应答的hash 以便构成hash_claim
    private final ArrayList<ByteString> allHash = new ArrayList<>();

    // 提取私钥
    private static final PrivateKey privateKey = ApiCoordTools.getPrivateKey("api\\jks\\312143999852503256.apiAckSignA.jks",
            "123", "312143999852503256.apiacksigna");

    // 模拟账户交易历史集合映射
    private final HashMap<String, TranRecord> AccountHistoryArray = ApiCoordTools.getAccountHistoryArray();

    public void start(ArrayList<ArrayList<String>> requestAccountsArray) {
        logger.info("client start");

        // cb(Result, Signature)
        Boolean b_req = false;
        Header bh = ApiCoordTools.getHeader(b_req, false, seq++);
        ArrayList<TranRecord> accountHistory = ApiCoordTools.getAccountHistory(requestAccountsArray.get(requestAccountsIndex++), AccountHistoryArray);
        Signature signature = ApiCoordTools.getSignature(ApiServClient.getCertId().getCreditCode(), accountHistory.toString(), privateKey);
        ResultSignature requestResultSignature = ApiCoordTools.getResultSignature(bh, accountHistory, signature);

        // response signature
        Signature response = blockingStub.cb(requestResultSignature);
        logger.info("cb getHash: " + response.getHash());

        // 接口调用存证
        ApiCoordTools.certificate(requestResultSignature.getResult().getBh(), requestResultSignature.getSignature(), response);
        allHash.add(requestResultSignature.getSignature().getHash());
        allHash.add(response.getHash());

        // ci(Result, Signature)
        bh = ApiCoordTools.getHeader(b_req, false, seq++);
        accountHistory = ApiCoordTools.getAccountHistory(requestAccountsArray.get(requestAccountsIndex++), AccountHistoryArray);
        signature = ApiCoordTools.getSignature(ApiServClient.getCertId().getCreditCode(), accountHistory.toString(), privateKey);
        requestResultSignature = ApiCoordTools.getResultSignature(bh, accountHistory, signature);

        // response signature
        response = blockingStub.ci(requestResultSignature);
        logger.info("ci getHash: " + response.getHash());

        // 接口调用存证
        ApiCoordTools.certificate(requestResultSignature.getResult().getBh(), requestResultSignature.getSignature(), response);
        allHash.add(requestResultSignature.getSignature().getHash());
        allHash.add(response.getHash());

        // ce(Result, Signature, Signature)
        bh = ApiCoordTools.getHeader(b_req, true, seq);
        accountHistory = ApiCoordTools.getAccountHistory(requestAccountsArray.get(requestAccountsIndex++), AccountHistoryArray);
        signature = ApiCoordTools.getSignature(ApiServClient.getCertId().getCreditCode(), accountHistory.toString(), privateKey);
        Signature signature2 = ApiCoordTools.getSignature(ApiServClient.getCertId().getCreditCode(), allHash.toString(), privateKey);
        ResultSignature2 requestResultSignature2 = ApiCoordTools.getResultSignature2(bh, accountHistory, signature, signature2);

        // response signature
        Signature2 response2 = blockingStub.ce(requestResultSignature2);
        logger.info("ce getHash: " + response2.getSignature().getHash());
        logger.info("ce getHash: " + response2.getSignature2().getHash());

        // 接口调用存证
        ApiCoordTools.certificate(requestResultSignature2.getResult().getBh(), requestResultSignature2.getSignature(), response2.getSignature());
        ApiCoordTools.certificate(requestResultSignature2.getResult().getBh(), requestResultSignature2.getSignature2(), response2.getSignature2());
    }

//    public static void main(String[] args) throws Exception {
//        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:50052").usePlaintext().build();
//        try {
//            TranQueryCallbackClient client = new TranQueryCallbackClient(channel);
//            client.start(null);
//        } finally {
//            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
//        }
//    }
}