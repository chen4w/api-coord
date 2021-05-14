package examples.apicoord.request;

import examples.ApiCoordTools;
import examples.apicoord.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static apicooperation.ApiBaseClient.ApireqClient;

public class TranQueryCallbackServer {
    private static final Logger logger = Logger.getLogger(TranQueryCallbackServer.class.getName());
    private Server server;

    private static final PrivateKey privateKey = ApiCoordTools.getPrivateKey("api\\jks\\275825005723476137.apiReqSignA.jks",
            "123", "275825005723476137.apireqsigna");

    private void start() throws IOException {
        int port = 50052;
        server = ServerBuilder.forPort(port)
                .addService(new TranQueryCallbackImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                TranQueryCallbackServer.this.stop();
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
        final TranQueryCallbackServer server = new TranQueryCallbackServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class TranQueryCallbackImpl extends TranQueryCallbackGrpc.TranQueryCallbackImplBase {
        @Override
        public void cb(ResultSignature request, StreamObserver<Signature> responseObserver) {
            Signature response = ApiCoordTools.getSignature(ApireqClient.getCertId().getCreditCode(),
                    request.getResult().getTsList().toString(), privateKey);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void ci(ResultSignature request, StreamObserver<Signature> responseObserver) {
            cb(request, responseObserver);
        }

        @Override
        public void ce(ResultSignature2 request, StreamObserver<Signature2> responseObserver) {
            Signature signature1 = ApiCoordTools.getSignature(ApireqClient.getCertId().getCreditCode(),
                    request.getResult().getTsList().toString(), privateKey);
            Signature signature2 = ApiCoordTools.getSignature(ApireqClient.getCertId().getCreditCode(),
                    request.getSignature2().getHash().toString(), privateKey);
            Signature2 response = Signature2.newBuilder().setSignature(signature1).setSignature2(signature2).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}