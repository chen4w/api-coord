package examples.apicoord.server;

import examples.ApiCoordTools;
import examples.apicoord.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static apicooperation.ApiBaseClient.ApiServClient;

public class TranQueryServer {
    private static final Logger logger = Logger.getLogger(TranQueryServer.class.getName());
    private Server server;

    // 提取私钥
    private static final PrivateKey privateKey = ApiCoordTools.getPrivateKey("api\\jks\\312143999852503256.apiAckSignA.jks",
            "123", "312143999852503256.apiacksigna");
    private static final ArrayList<ArrayList<String>> requestAccountsArray = new ArrayList();

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new TranQueryImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                TranQueryServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final TranQueryServer server = new TranQueryServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class TranQueryImpl extends TranQueryGrpc.TranQueryImplBase {
        @Override
        public void rb(ReqRbSignature request, StreamObserver<examples.apicoord.Signature> responseObserver) {
            Signature response = ApiCoordTools.getSignature(ApiServClient.getCertId().getCreditCode(),
                    request.getSignature().getHash().toString(), privateKey);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void ri(ReqRiReSignature request, StreamObserver<examples.apicoord.Signature> responseObserver) {
            Signature response = ApiCoordTools.getSignature(ApiServClient.getCertId().getCreditCode(),
                    request.getReqRiRe().getAccountsList().toString(), privateKey);
            ArrayList tmpArrayList = new ArrayList();
            for (int i = 0; i < request.getReqRiRe().getAccountsCount(); i++)
                tmpArrayList.add(request.getReqRiRe().getAccounts(i));
            requestAccountsArray.add(tmpArrayList);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void re(ReqRiReSignature request, StreamObserver<Signature> responseObserver) {
            ri(request, responseObserver);
            ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:50052").usePlaintext().build();
            try {
                TranQueryCallbackClient client = new TranQueryCallbackClient(channel);
                client.start(requestAccountsArray);
            } finally {
                try {
                    channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}