package examples.apicoord;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: apicoord.proto")
public final class TranQueryCallbackGrpc {

  private TranQueryCallbackGrpc() {}

  public static final String SERVICE_NAME = "apicoord.TranQueryCallback";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<examples.apicoord.ResultSignature,
      examples.apicoord.Signature> getCbMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cb",
      requestType = examples.apicoord.ResultSignature.class,
      responseType = examples.apicoord.Signature.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.apicoord.ResultSignature,
      examples.apicoord.Signature> getCbMethod() {
    io.grpc.MethodDescriptor<examples.apicoord.ResultSignature, examples.apicoord.Signature> getCbMethod;
    if ((getCbMethod = TranQueryCallbackGrpc.getCbMethod) == null) {
      synchronized (TranQueryCallbackGrpc.class) {
        if ((getCbMethod = TranQueryCallbackGrpc.getCbMethod) == null) {
          TranQueryCallbackGrpc.getCbMethod = getCbMethod =
              io.grpc.MethodDescriptor.<examples.apicoord.ResultSignature, examples.apicoord.Signature>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "cb"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.ResultSignature.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.Signature.getDefaultInstance()))
              .setSchemaDescriptor(new TranQueryCallbackMethodDescriptorSupplier("cb"))
              .build();
        }
      }
    }
    return getCbMethod;
  }

  private static volatile io.grpc.MethodDescriptor<examples.apicoord.ResultSignature,
      examples.apicoord.Signature> getCiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ci",
      requestType = examples.apicoord.ResultSignature.class,
      responseType = examples.apicoord.Signature.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.apicoord.ResultSignature,
      examples.apicoord.Signature> getCiMethod() {
    io.grpc.MethodDescriptor<examples.apicoord.ResultSignature, examples.apicoord.Signature> getCiMethod;
    if ((getCiMethod = TranQueryCallbackGrpc.getCiMethod) == null) {
      synchronized (TranQueryCallbackGrpc.class) {
        if ((getCiMethod = TranQueryCallbackGrpc.getCiMethod) == null) {
          TranQueryCallbackGrpc.getCiMethod = getCiMethod =
              io.grpc.MethodDescriptor.<examples.apicoord.ResultSignature, examples.apicoord.Signature>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ci"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.ResultSignature.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.Signature.getDefaultInstance()))
              .setSchemaDescriptor(new TranQueryCallbackMethodDescriptorSupplier("ci"))
              .build();
        }
      }
    }
    return getCiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<examples.apicoord.ResultSignature2,
      examples.apicoord.Signature2> getCeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ce",
      requestType = examples.apicoord.ResultSignature2.class,
      responseType = examples.apicoord.Signature2.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.apicoord.ResultSignature2,
      examples.apicoord.Signature2> getCeMethod() {
    io.grpc.MethodDescriptor<examples.apicoord.ResultSignature2, examples.apicoord.Signature2> getCeMethod;
    if ((getCeMethod = TranQueryCallbackGrpc.getCeMethod) == null) {
      synchronized (TranQueryCallbackGrpc.class) {
        if ((getCeMethod = TranQueryCallbackGrpc.getCeMethod) == null) {
          TranQueryCallbackGrpc.getCeMethod = getCeMethod =
              io.grpc.MethodDescriptor.<examples.apicoord.ResultSignature2, examples.apicoord.Signature2>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.ResultSignature2.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.Signature2.getDefaultInstance()))
              .setSchemaDescriptor(new TranQueryCallbackMethodDescriptorSupplier("ce"))
              .build();
        }
      }
    }
    return getCeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TranQueryCallbackStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TranQueryCallbackStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TranQueryCallbackStub>() {
        @java.lang.Override
        public TranQueryCallbackStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TranQueryCallbackStub(channel, callOptions);
        }
      };
    return TranQueryCallbackStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TranQueryCallbackBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TranQueryCallbackBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TranQueryCallbackBlockingStub>() {
        @java.lang.Override
        public TranQueryCallbackBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TranQueryCallbackBlockingStub(channel, callOptions);
        }
      };
    return TranQueryCallbackBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TranQueryCallbackFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TranQueryCallbackFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TranQueryCallbackFutureStub>() {
        @java.lang.Override
        public TranQueryCallbackFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TranQueryCallbackFutureStub(channel, callOptions);
        }
      };
    return TranQueryCallbackFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TranQueryCallbackImplBase implements io.grpc.BindableService {

    /**
     */
    public void cb(examples.apicoord.ResultSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCbMethod(), responseObserver);
    }

    /**
     */
    public void ci(examples.apicoord.ResultSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCiMethod(), responseObserver);
    }

    /**
     */
    public void ce(examples.apicoord.ResultSignature2 request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature2> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCbMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                examples.apicoord.ResultSignature,
                examples.apicoord.Signature>(
                  this, METHODID_CB)))
          .addMethod(
            getCiMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                examples.apicoord.ResultSignature,
                examples.apicoord.Signature>(
                  this, METHODID_CI)))
          .addMethod(
            getCeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                examples.apicoord.ResultSignature2,
                examples.apicoord.Signature2>(
                  this, METHODID_CE)))
          .build();
    }
  }

  /**
   */
  public static final class TranQueryCallbackStub extends io.grpc.stub.AbstractAsyncStub<TranQueryCallbackStub> {
    private TranQueryCallbackStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranQueryCallbackStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TranQueryCallbackStub(channel, callOptions);
    }

    /**
     */
    public void cb(examples.apicoord.ResultSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCbMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ci(examples.apicoord.ResultSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ce(examples.apicoord.ResultSignature2 request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature2> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TranQueryCallbackBlockingStub extends io.grpc.stub.AbstractBlockingStub<TranQueryCallbackBlockingStub> {
    private TranQueryCallbackBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranQueryCallbackBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TranQueryCallbackBlockingStub(channel, callOptions);
    }

    /**
     */
    public examples.apicoord.Signature cb(examples.apicoord.ResultSignature request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCbMethod(), getCallOptions(), request);
    }

    /**
     */
    public examples.apicoord.Signature ci(examples.apicoord.ResultSignature request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCiMethod(), getCallOptions(), request);
    }

    /**
     */
    public examples.apicoord.Signature2 ce(examples.apicoord.ResultSignature2 request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TranQueryCallbackFutureStub extends io.grpc.stub.AbstractFutureStub<TranQueryCallbackFutureStub> {
    private TranQueryCallbackFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranQueryCallbackFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TranQueryCallbackFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<examples.apicoord.Signature> cb(
        examples.apicoord.ResultSignature request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCbMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<examples.apicoord.Signature> ci(
        examples.apicoord.ResultSignature request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<examples.apicoord.Signature2> ce(
        examples.apicoord.ResultSignature2 request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CB = 0;
  private static final int METHODID_CI = 1;
  private static final int METHODID_CE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TranQueryCallbackImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TranQueryCallbackImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CB:
          serviceImpl.cb((examples.apicoord.ResultSignature) request,
              (io.grpc.stub.StreamObserver<examples.apicoord.Signature>) responseObserver);
          break;
        case METHODID_CI:
          serviceImpl.ci((examples.apicoord.ResultSignature) request,
              (io.grpc.stub.StreamObserver<examples.apicoord.Signature>) responseObserver);
          break;
        case METHODID_CE:
          serviceImpl.ce((examples.apicoord.ResultSignature2) request,
              (io.grpc.stub.StreamObserver<examples.apicoord.Signature2>) responseObserver);
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

  private static abstract class TranQueryCallbackBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TranQueryCallbackBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return examples.apicoord.ApiCoordProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TranQueryCallback");
    }
  }

  private static final class TranQueryCallbackFileDescriptorSupplier
      extends TranQueryCallbackBaseDescriptorSupplier {
    TranQueryCallbackFileDescriptorSupplier() {}
  }

  private static final class TranQueryCallbackMethodDescriptorSupplier
      extends TranQueryCallbackBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TranQueryCallbackMethodDescriptorSupplier(String methodName) {
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
      synchronized (TranQueryCallbackGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TranQueryCallbackFileDescriptorSupplier())
              .addMethod(getCbMethod())
              .addMethod(getCiMethod())
              .addMethod(getCeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
