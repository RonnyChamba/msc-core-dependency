package com.dependency.mscore.grpc.component;

import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GrpcChannelManager {

    private final Map<String, ManagedChannel> grpcChannelMap = new ConcurrentHashMap<>();

    public void setManagedChannel(String server, ManagedChannel channel) {
        grpcChannelMap.put(server, channel);
    }

    public ManagedChannel getManagedChannel(String server) {
        return grpcChannelMap.get(server);
    }

}
