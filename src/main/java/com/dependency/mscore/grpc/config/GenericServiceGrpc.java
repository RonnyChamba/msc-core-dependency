package com.dependency.mscore.grpc.config;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * define the service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.38.1)",
    comments = "Source: generic-service.proto")
public final class GenericServiceGrpc {

  private GenericServiceGrpc() {}

  public static final String SERVICE_NAME = "com.dependency.mscore.grpc.config.GenericService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dependency.mscore.grpc.config.GenericRequest,
      com.dependency.mscore.grpc.config.GenericResponse> getCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "call",
      requestType = com.dependency.mscore.grpc.config.GenericRequest.class,
      responseType = com.dependency.mscore.grpc.config.GenericResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dependency.mscore.grpc.config.GenericRequest,
      com.dependency.mscore.grpc.config.GenericResponse> getCallMethod() {
    io.grpc.MethodDescriptor<com.dependency.mscore.grpc.config.GenericRequest, com.dependency.mscore.grpc.config.GenericResponse> getCallMethod;
    if ((getCallMethod = GenericServiceGrpc.getCallMethod) == null) {
      synchronized (GenericServiceGrpc.class) {
        if ((getCallMethod = GenericServiceGrpc.getCallMethod) == null) {
          GenericServiceGrpc.getCallMethod = getCallMethod =
              io.grpc.MethodDescriptor.<com.dependency.mscore.grpc.config.GenericRequest, com.dependency.mscore.grpc.config.GenericResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dependency.mscore.grpc.config.GenericRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dependency.mscore.grpc.config.GenericResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenericServiceMethodDescriptorSupplier("call"))
              .build();
        }
      }
    }
    return getCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GenericServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenericServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenericServiceStub>() {
        @java.lang.Override
        public GenericServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenericServiceStub(channel, callOptions);
        }
      };
    return GenericServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GenericServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenericServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenericServiceBlockingStub>() {
        @java.lang.Override
        public GenericServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenericServiceBlockingStub(channel, callOptions);
        }
      };
    return GenericServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GenericServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenericServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenericServiceFutureStub>() {
        @java.lang.Override
        public GenericServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenericServiceFutureStub(channel, callOptions);
        }
      };
    return GenericServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * define the service
   * </pre>
   */
  public static abstract class GenericServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void call(com.dependency.mscore.grpc.config.GenericRequest request,
        io.grpc.stub.StreamObserver<com.dependency.mscore.grpc.config.GenericResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.dependency.mscore.grpc.config.GenericRequest,
                com.dependency.mscore.grpc.config.GenericResponse>(
                  this, METHODID_CALL)))
          .build();
    }
  }

  /**
   * <pre>
   * define the service
   * </pre>
   */
  public static final class GenericServiceStub extends io.grpc.stub.AbstractAsyncStub<GenericServiceStub> {
    private GenericServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenericServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenericServiceStub(channel, callOptions);
    }

    /**
     */
    public void call(com.dependency.mscore.grpc.config.GenericRequest request,
        io.grpc.stub.StreamObserver<com.dependency.mscore.grpc.config.GenericResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * define the service
   * </pre>
   */
  public static final class GenericServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<GenericServiceBlockingStub> {
    private GenericServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenericServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenericServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.dependency.mscore.grpc.config.GenericResponse call(com.dependency.mscore.grpc.config.GenericRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * define the service
   * </pre>
   */
  public static final class GenericServiceFutureStub extends io.grpc.stub.AbstractFutureStub<GenericServiceFutureStub> {
    private GenericServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenericServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenericServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dependency.mscore.grpc.config.GenericResponse> call(
        com.dependency.mscore.grpc.config.GenericRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GenericServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GenericServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL:
          serviceImpl.call((com.dependency.mscore.grpc.config.GenericRequest) request,
              (io.grpc.stub.StreamObserver<com.dependency.mscore.grpc.config.GenericResponse>) responseObserver);
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

  private static abstract class GenericServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GenericServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dependency.mscore.grpc.config.GenericServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GenericService");
    }
  }

  private static final class GenericServiceFileDescriptorSupplier
      extends GenericServiceBaseDescriptorSupplier {
    GenericServiceFileDescriptorSupplier() {}
  }

  private static final class GenericServiceMethodDescriptorSupplier
      extends GenericServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GenericServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GenericServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GenericServiceFileDescriptorSupplier())
              .addMethod(getCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
