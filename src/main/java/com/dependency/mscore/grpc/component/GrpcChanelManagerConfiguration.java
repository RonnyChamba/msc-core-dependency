package com.dependency.mscore.grpc.component;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcChanelManagerConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(GrpcChanelManagerConfiguration.class);

    @Value("#{${grpc.client-server.map:null}}")
    private Map<String, String> grpcServerMap;

    @Value("${grpc.client.timeout.idle:5}")
    private int grpcIdleTimeout;

    @Value("${grpc.client.inbound.message.size:5242880}")
    private int inboundMessageSize;

    @Autowired
    private GrpcChannelManager channelManager;

    @PostConstruct
    public void initGrpcConnection() {
        if (Objects.nonNull(grpcServerMap) && !grpcServerMap.isEmpty()) {
            LOGGER.info("Connecting to gRPC Servers : {}", grpcServerMap);
            ManagedChannel channel = null;
            ConnectivityState currentState;
            for (Map.Entry<String, String> entry : grpcServerMap.entrySet()) {
                String serverClient = entry.getValue();
                LOGGER.info("Openning gRPC channel for server : {}", serverClient);
                channel = ManagedChannelBuilder.forTarget(serverClient)
                        .usePlaintext()
                        .maxInboundMessageSize(inboundMessageSize)
                        .idleTimeout(grpcIdleTimeout, TimeUnit.MINUTES)
                        .build();
                currentState = channel.getState(false);
                LOGGER.info("Channel opened for server : {}, with status : {}", serverClient, currentState);
                channelManager.setManagedChannel(serverClient, channel);
            }
        }

    }

}
