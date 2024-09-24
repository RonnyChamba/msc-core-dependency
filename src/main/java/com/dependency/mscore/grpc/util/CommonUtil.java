package com.dependency.mscore.grpc.util;

import com.dependency.mscore.grpc.component.GrpcChannelManager;
import com.dependency.mscore.grpc.config.GenericServiceGrpc;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CommonUtil {
    private static GrpcChannelManager channelManager;

    @Autowired
    public CommonUtil(GrpcChannelManager manager) {
        channelManager = manager;
    }

    public static GenericServiceGrpc.GenericServiceBlockingStub getBlockingStub(String serverClient, int grpcIdleTimeout, int grpcDeadlineTimeout) {
        return channelConfiguration(serverClient, grpcIdleTimeout, grpcDeadlineTimeout, 5242880, 5242880);
    }

    private static GenericServiceGrpc.GenericServiceBlockingStub channelConfiguration(String serverClient, int grpcIdleTimeout, int grpcDeadlineTimeout, int inboundMessageSize, int outboundMessageSize) {
        if (serverClient != null && !serverClient.isEmpty()) {
            ManagedChannel channel = channelManager.getManagedChannel(serverClient);
            if (channel == null) {
                channel = grpcInitConnection(inboundMessageSize, grpcIdleTimeout, serverClient);
            } else {
                ConnectivityState currentState = channel.getState(false);
                if (currentState.toString().equalsIgnoreCase(ConnectivityState.SHUTDOWN.toString()) || channel.isShutdown()) {
//                    log.info("Channel for server : {} was closed...", serverClient);
                    System.out.println("Channel for server : " + serverClient + " was closed...");
                    channel = grpcInitConnection(inboundMessageSize, grpcIdleTimeout, serverClient);
                }
            }

            return GenericServiceGrpc.newBlockingStub(channel);
//            return (HelloServiceGrpc.HelloServiceBlockingStub) ((HelloServiceGrpc.HelloServiceBlockingStub) (HelloServiceGrpc.HelloServiceBlockingStub) (HelloServiceGrpc.HelloServiceBlockingStub) ((HelloServiceGrpc.HelloServiceBlockingStub) HelloServiceGrpc.newBlockingStub(channel).withWaitForReady()).withMaxOutboundMessageSize(outboundMessageSize)).withMaxInboundMessageSize(inboundMessageSize)).withDeadlineAfter((long) grpcDeadlineTimeout, TimeUnit.SECONDS)).withInterceptors(new ClientInterceptor[]{MetadataUtils.newAttachHeadersInterceptor(createDevelopmentDestinationServiceMetadata(serverClient))});
        } else {
            return null;
        }
    }

    private static ManagedChannel grpcInitConnection(int inboundMessageSize, int grpcIdleTimeout, String serverClient) {
        ManagedChannel channel = null;
        System.out.println("Openning gRPC channel for server : " + serverClient);
//        log.info("Openning gRPC channel for server : {}", serverClient);
        channel = ManagedChannelBuilder.forTarget(serverClient).usePlaintext().maxInboundMessageSize(inboundMessageSize).idleTimeout((long) grpcIdleTimeout, TimeUnit.MINUTES).build();
        ConnectivityState currentState = channel.getState(false);
        System.out.println("Channel opened for server : " + serverClient + ", with status : " + currentState);
//        log.info("Channel opened for server : {}, with status : {}", serverClient, currentState);
        channelManager.setManagedChannel(serverClient, channel);
        return channel;
    }

}
