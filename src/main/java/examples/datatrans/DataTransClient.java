package examples.datatrans;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import com.google.common.io.ByteSink;
import com.google.common.io.Files;
import com.google.common.io.FileWriteMode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.UUID;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import model.ReqAckProof;
import static apicooperation.ApiBaseClient.ApiServClient;
import static apicooperation.ApiBaseClient.ApireqClient;
import static apicooperation.RegisterApi.*;
import static apicooperation.RegisterApi.apireqClient;

public class DataTransClient {
    private static final Logger logger = Logger.getLogger(DataTransClient.class.getName());

    private final DataTransGrpc.DataTransBlockingStub blockingStub;

    public DataTransClient(Channel channel) {
        blockingStub = DataTransGrpc.newBlockingStub(channel);
    }

    public void greet(String name) throws Exception {
        logger.info("Will try to greet " + name + " ...");
        DataTransRequest request = DataTransRequest.newBuilder().setUrl(name).build();

        File tempFile = File.createTempFile("tempFile", ".txt");
        ByteSink byteSink = Files.asByteSink(tempFile, FileWriteMode.APPEND);
        Iterator<Data> response;
        try {
            try {
                response = blockingStub.transmission(request);
                while (response.hasNext()) {
                    byteSink.write(response.next().getByte().toByteArray());
                }
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
                return;
            }
            try (FileReader fr = new FileReader(tempFile);
                 BufferedReader br = new BufferedReader(fr)) {
                String nextLine;
                while ((nextLine = br.readLine()) != null) {
                    logger.info(nextLine);
                    TranPostClient postClient = new TranPostClient("192.168.2.69:8081");
                    ReqAckProof reqProof = new ReqAckProof("req123456789", ackReceiveId, serviceId, "", true, false, 1, nextLine, "hash456", System.currentTimeMillis(),
                            new ReqAckProof.Signature(ApireqClient.getCertId().getCreditCode(), "apiReqSignA", nextLine, System.currentTimeMillis(), "sign"),
                            new ReqAckProof.Signature(ApiServClient.getCertId().getCreditCode(), "apiAckSignA", "hash", System.currentTimeMillis(), "sign"));

                    String tranId = UUID.randomUUID().toString().replace("-", "");
                    Peer.Transaction tran = apireqClient.getTranCreator().createInvokeTran(tranId, apireqClient.getCertId(),
                            apireqClient.getApiCooperationId(), "reqAckProof", JSON.toJSONString(reqProof));
                    JSONObject res = postClient.postSignedTran(tran);
                    System.out.println(res);
                }
            }
        } finally {
            tempFile.delete();
        }
    }

    public static void main(String[] args) throws Exception {
        String user = "url";
        String target = "localhost:50051";
        if (args.length > 0) {
            if ("--help".equals(args[0])) {
                System.err.println("Usage: [name [target]]");
                System.err.println("");
                System.err.println("  name    The name you wish to be greeted by. Defaults to " + user);
                System.err.println("  target  The server to connect to. Defaults to " + target);
                System.exit(1);
            }
            user = args[0];
        }
        if (args.length > 1) {
            target = args[1];
        }

        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
        try {
            DataTransClient client = new DataTransClient(channel);
            client.greet(user);
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}