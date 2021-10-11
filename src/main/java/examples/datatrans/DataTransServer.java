package examples.datatrans;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DataTransServer {
    private static final Logger logger = Logger.getLogger(DataTransServer.class.getName());

    private Server server;

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new DataTransImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                DataTransServer.this.stop();
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
        final DataTransServer server = new DataTransServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class DataTransImpl extends DataTransGrpc.DataTransImplBase {

        @Override
        public void transmission(DataTransRequest req, StreamObserver<Data> responseObserver) {
            File tempFile = null;
            try {
                tempFile = File.createTempFile("tempFile", ".txt");
                try (FileWriter fw = new FileWriter(tempFile);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                    int numBytes = 0;
                    String tmpString = "1234567\n";
                    while (numBytes < 32) { // 1024k -> 32B
                        bw.write(tmpString);
                        numBytes += tmpString.length();
                    }
                }
                try (FileInputStream fis = new FileInputStream(tempFile);
                     BufferedInputStream bis = new BufferedInputStream(fis)) {
                    int bufferSize = 8;// 256k -> 8B
                    byte[] buffer = new byte[bufferSize];
                    int length;
                    while ((length = bis.read(buffer, 0, bufferSize)) != -1) {
                        responseObserver.onNext(Data.newBuilder().setByte(ByteString.copyFrom(buffer, 0, length)).build());
                    }
                    responseObserver.onCompleted();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (tempFile != null) {
                    tempFile.delete();
                }
            }
        }
    }
}
