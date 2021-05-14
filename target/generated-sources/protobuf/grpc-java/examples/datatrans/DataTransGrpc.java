package examples.datatrans;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: datatrans.proto")
public final class DataTransGrpc {

  private DataTransGrpc() {}

  public static final String SERVICE_NAME = "datatrans.DataTrans";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<examples.datatrans.DataTransRequest,
      examples.datatrans.Data> getTransmissionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Transmission",
      requestType = examples.datatrans.DataTransRequest.class,
      responseType = examples.datatrans.Data.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<examples.datatrans.DataTransRequest,
      examples.datatrans.Data> getTransmissionMethod() {
    io.grpc.MethodDescriptor<examples.datatrans.DataTransRequest, examples.datatrans.Data> getTransmissionMethod;
    if ((getTransmissionMethod = DataTransGrpc.getTransmissionMethod) == null) {
      synchronized (DataTransGrpc.class) {
        if ((getTransmissionMethod = DataTransGrpc.getTransmissionMethod) == null) {
          DataTransGrpc.getTransmissionMethod = getTransmissionMethod =
              io.grpc.MethodDescriptor.<examples.datatrans.DataTransRequest, examples.datatrans.Data>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Transmission"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.datatrans.DataTransRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.datatrans.Data.getDefaultInstance()))
              .setSchemaDescriptor(new DataTransMethodDescriptorSupplier("Transmission"))
              .build();
        }
      }
    }
    return getTransmissionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DataTransStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataTransStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataTransStub>() {
        @java.lang.Override
        public DataTransStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataTransStub(channel, callOptions);
        }
      };
    return DataTransStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DataTransBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataTransBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataTransBlockingStub>() {
        @java.lang.Override
        public DataTransBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataTransBlockingStub(channel, callOptions);
        }
      };
    return DataTransBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DataTransFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataTransFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataTransFutureStub>() {
        @java.lang.Override
        public DataTransFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataTransFutureStub(channel, callOptions);
        }
      };
    return DataTransFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DataTransImplBase implements io.grpc.BindableService {

    /**
     */
    public void transmission(examples.datatrans.DataTransRequest request,
        io.grpc.stub.StreamObserver<examples.datatrans.Data> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTransmissionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTransmissionMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                examples.datatrans.DataTransRequest,
                examples.datatrans.Data>(
                  this, METHODID_TRANSMISSION)))
          .build();
    }
  }

  /**
   */
  public static final class DataTransStub extends io.grpc.stub.AbstractAsyncStub<DataTransStub> {
    private DataTransStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataTransStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataTransStub(channel, callOptions);
    }

    /**
     */
    public void transmission(examples.datatrans.DataTransRequest request,
        io.grpc.stub.StreamObserver<examples.datatrans.Data> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getTransmissionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DataTransBlockingStub extends io.grpc.stub.AbstractBlockingStub<DataTransBlockingStub> {
    private DataTransBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataTransBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataTransBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<examples.datatrans.Data> transmission(
        examples.datatrans.DataTransRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getTransmissionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DataTransFutureStub extends io.grpc.stub.AbstractFutureStub<DataTransFutureStub> {
    private DataTransFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataTransFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataTransFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_TRANSMISSION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DataTransImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DataTransImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRANSMISSION:
          serviceImpl.transmission((examples.datatrans.DataTransRequest) request,
              (io.grpc.stub.StreamObserver<examples.datatrans.Data>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DataTransBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DataTransBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return examples.datatrans.DataTransProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DataTrans");
    }
  }

  private static final class DataTransFileDescriptorSupplier
      extends DataTransBaseDescriptorSupplier {
    DataTransFileDescriptorSupplier() {}
  }

  private static final class DataTransMethodDescriptorSupplier
      extends DataTransBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DataTransMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DataTransGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DataTransFileDescriptorSupplier())
              .addMethod(getTransmissionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
