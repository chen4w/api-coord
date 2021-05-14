package examples.apicoord.request;

import examples.ApiCoordTools;
import examples.apicoord.*;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static apicooperation.ApiBaseClient.ApireqClient;

public class TranQueryClient {
    private static final Logger logger = Logger.getLogger(TranQueryClient.class.getName());
    private final TranQueryGrpc.TranQueryBlockingStub blockingStub;
    public TranQueryClient(Channel channel) {
        blockingStub = TranQueryGrpc.newBlockingStub(channel);
    }

    private int seq = 1;

    // 提取私钥
    private static final PrivateKey privateKey = ApiCoordTools.getPrivateKey("api\\jks\\275825005723476137.apiReqSignA.jks",
            "123", "275825005723476137.apireqsigna");

    // 模拟目标账户集合 2次ri 每次查询2个账户交易历史
    private final ArrayList<ArrayList<String>> requestAccountsArray = new ArrayList(Arrays.asList(
            new ArrayList(Arrays.asList("account1", "account2")),
            new ArrayList(Arrays.asList("account3", "account4"))));
    // 模拟目标账户集合 1次re 每次查询2个账户交易历史
    ArrayList<String> requestAccounts = new ArrayList(Arrays.asList("account5", "account6"));

    public void start() {
        logger.info("client start");

        // rb(Req_rb, Signature)
        Boolean b_req = true;
        Header bh = ApiCoordTools.getHeader(b_req, false, seq++);
        Signature signature = ApiCoordTools.getSignature(ApireqClient.getCertId().getCreditCode(), "begin", privateKey);
        ReqRbSignature requestRb = ApiCoordTools.getReqRbSignature(bh, System.currentTimeMillis(), System.currentTimeMillis(), signature);

        // response signature
        Signature response = blockingStub.rb(requestRb);
        logger.info("rb getHash: " + response.getHash());

        // 接口调用存证
        ApiCoordTools.certificate(requestRb.getReqRb().getBh(), requestRb.getSignature(), response);

        // 2次ri
        ReqRiReSignature requestRiRe;
        for (ArrayList<String> requestAccounts : requestAccountsArray) {
            // ri(Req_ri_re, Signature)
            bh = ApiCoordTools.getHeader(b_req, false, seq++);
            signature = ApiCoordTools.getSignature(ApireqClient.getCertId().getCreditCode(), requestAccounts.toString(), privateKey);
            requestRiRe = ApiCoordTools.getReqRiReSignature(bh, requestAccounts, signature);

            // response signature
            response = blockingStub.ri(requestRiRe);
            logger.info("ri getHash: " + response.getHash());

            // 接口调用存证
            ApiCoordTools.certificate(requestRiRe.getReqRiRe().getBh(), requestRiRe.getSignature(), response);
        }

        // re(Req_ri_re, Signature)
        bh = ApiCoordTools.getHeader(b_req, true, seq);
        signature = ApiCoordTools.getSignature(ApireqClient.getCertId().getCreditCode(), requestAccounts.toString(), privateKey);
        requestRiRe = ApiCoordTools.getReqRiReSignature(bh, requestAccounts, signature);

        // response signature
        response = blockingStub.re(requestRiRe);
        logger.info("re getHash: " + response.getHash());

        // 接口调用存证
        ApiCoordTools.certificate(requestRiRe.getReqRiRe().getBh(), requestRiRe.getSignature(), response);
    }

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:50051").usePlaintext().build();
        try {
            TranQueryClient client = new TranQueryClient(channel);
            client.start();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}