package examples.apicoord;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: apicoord.proto")
public final class TranQueryGrpc {

  private TranQueryGrpc() {}

  public static final String SERVICE_NAME = "apicoord.TranQuery";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<examples.apicoord.ReqRbSignature,
      examples.apicoord.Signature> getRbMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rb",
      requestType = examples.apicoord.ReqRbSignature.class,
      responseType = examples.apicoord.Signature.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.apicoord.ReqRbSignature,
      examples.apicoord.Signature> getRbMethod() {
    io.grpc.MethodDescriptor<examples.apicoord.ReqRbSignature, examples.apicoord.Signature> getRbMethod;
    if ((getRbMethod = TranQueryGrpc.getRbMethod) == null) {
      synchronized (TranQueryGrpc.class) {
        if ((getRbMethod = TranQueryGrpc.getRbMethod) == null) {
          TranQueryGrpc.getRbMethod = getRbMethod =
              io.grpc.MethodDescriptor.<examples.apicoord.ReqRbSignature, examples.apicoord.Signature>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rb"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.ReqRbSignature.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.Signature.getDefaultInstance()))
              .setSchemaDescriptor(new TranQueryMethodDescriptorSupplier("rb"))
              .build();
        }
      }
    }
    return getRbMethod;
  }

  private static volatile io.grpc.MethodDescriptor<examples.apicoord.ReqRiReSignature,
      examples.apicoord.Signature> getRiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ri",
      requestType = examples.apicoord.ReqRiReSignature.class,
      responseType = examples.apicoord.Signature.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.apicoord.ReqRiReSignature,
      examples.apicoord.Signature> getRiMethod() {
    io.grpc.MethodDescriptor<examples.apicoord.ReqRiReSignature, examples.apicoord.Signature> getRiMethod;
    if ((getRiMethod = TranQueryGrpc.getRiMethod) == null) {
      synchronized (TranQueryGrpc.class) {
        if ((getRiMethod = TranQueryGrpc.getRiMethod) == null) {
          TranQueryGrpc.getRiMethod = getRiMethod =
              io.grpc.MethodDescriptor.<examples.apicoord.ReqRiReSignature, examples.apicoord.Signature>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ri"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.ReqRiReSignature.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.Signature.getDefaultInstance()))
              .setSchemaDescriptor(new TranQueryMethodDescriptorSupplier("ri"))
              .build();
        }
      }
    }
    return getRiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<examples.apicoord.ReqRiReSignature,
      examples.apicoord.Signature> getReMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "re",
      requestType = examples.apicoord.ReqRiReSignature.class,
      responseType = examples.apicoord.Signature.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.apicoord.ReqRiReSignature,
      examples.apicoord.Signature> getReMethod() {
    io.grpc.MethodDescriptor<examples.apicoord.ReqRiReSignature, examples.apicoord.Signature> getReMethod;
    if ((getReMethod = TranQueryGrpc.getReMethod) == null) {
      synchronized (TranQueryGrpc.class) {
        if ((getReMethod = TranQueryGrpc.getReMethod) == null) {
          TranQueryGrpc.getReMethod = getReMethod =
              io.grpc.MethodDescriptor.<examples.apicoord.ReqRiReSignature, examples.apicoord.Signature>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "re"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.ReqRiReSignature.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.apicoord.Signature.getDefaultInstance()))
              .setSchemaDescriptor(new TranQueryMethodDescriptorSupplier("re"))
              .build();
        }
      }
    }
    return getReMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TranQueryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TranQueryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TranQueryStub>() {
        @java.lang.Override
        public TranQueryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TranQueryStub(channel, callOptions);
        }
      };
    return TranQueryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TranQueryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TranQueryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TranQueryBlockingStub>() {
        @java.lang.Override
        public TranQueryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TranQueryBlockingStub(channel, callOptions);
        }
      };
    return TranQueryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TranQueryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TranQueryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TranQueryFutureStub>() {
        @java.lang.Override
        public TranQueryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TranQueryFutureStub(channel, callOptions);
        }
      };
    return TranQueryFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TranQueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void rb(examples.apicoord.ReqRbSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRbMethod(), responseObserver);
    }

    /**
     */
    public void ri(examples.apicoord.ReqRiReSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRiMethod(), responseObserver);
    }

    /**
     */
    public void re(examples.apicoord.ReqRiReSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRbMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                examples.apicoord.ReqRbSignature,
                examples.apicoord.Signature>(
                  this, METHODID_RB)))
          .addMethod(
            getRiMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                examples.apicoord.ReqRiReSignature,
                examples.apicoord.Signature>(
                  this, METHODID_RI)))
          .addMethod(
            getReMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                examples.apicoord.ReqRiReSignature,
                examples.apicoord.Signature>(
                  this, METHODID_RE)))
          .build();
    }
  }

  /**
   */
  public static final class TranQueryStub extends io.grpc.stub.AbstractAsyncStub<TranQueryStub> {
    private TranQueryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranQueryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TranQueryStub(channel, callOptions);
    }

    /**
     */
    public void rb(examples.apicoord.ReqRbSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRbMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ri(examples.apicoord.ReqRiReSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void re(examples.apicoord.ReqRiReSignature request,
        io.grpc.stub.StreamObserver<examples.apicoord.Signature> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TranQueryBlockingStub extends io.grpc.stub.AbstractBlockingStub<TranQueryBlockingStub> {
    private TranQueryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranQueryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TranQueryBlockingStub(channel, callOptions);
    }

    /**
     */
    public examples.apicoord.Signature rb(examples.apicoord.ReqRbSignature request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRbMethod(), getCallOptions(), request);
    }

    /**
     */
    public examples.apicoord.Signature ri(examples.apicoord.ReqRiReSignature request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRiMethod(), getCallOptions(), request);
    }

    /**
     */
    public examples.apicoord.Signature re(examples.apicoord.ReqRiReSignature request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TranQueryFutureStub extends io.grpc.stub.AbstractFutureStub<TranQueryFutureStub> {
    private TranQueryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TranQueryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TranQueryFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<examples.apicoord.Signature> rb(
        examples.apicoord.ReqRbSignature request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRbMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<examples.apicoord.Signature> ri(
        examples.apicoord.ReqRiReSignature request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<examples.apicoord.Signature> re(
        examples.apicoord.ReqRiReSignature request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RB = 0;
  private static final int METHODID_RI = 1;
  private static final int METHODID_RE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TranQueryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TranQueryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RB:
          serviceImpl.rb((examples.apicoord.ReqRbSignature) request,
              (io.grpc.stub.StreamObserver<examples.apicoord.Signature>) responseObserver);
          break;
        case METHODID_RI:
          serviceImpl.ri((examples.apicoord.ReqRiReSignature) request,
              (io.grpc.stub.StreamObserver<examples.apicoord.Signature>) responseObserver);
          break;
        case METHODID_RE:
          serviceImpl.re((examples.apicoord.ReqRiReSignature) request,
              (io.grpc.stub.StreamObserver<examples.apicoord.Signature>) responseObserver);
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

  private static abstract class TranQueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TranQueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return examples.apicoord.ApiCoordProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TranQuery");
    }
  }

  private static final class TranQueryFileDescriptorSupplier
      extends TranQueryBaseDescriptorSupplier {
    TranQueryFileDescriptorSupplier() {}
  }

  private static final class TranQueryMethodDescriptorSupplier
      extends TranQueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TranQueryMethodDescriptorSupplier(String methodName) {
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
      synchronized (TranQueryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TranQueryFileDescriptorSupplier())
              .addMethod(getRbMethod())
              .addMethod(getRiMethod())
              .addMethod(getReMethod())
              .build();
        }
      }
    }
    return result;
  }
}
