package com.dependency.mscore.grpc.dto.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenericProducerModel<T> {

    private String transactionId;
    private String service;
    private String option;
    private String origin;
    private String userRequest;
    private T payload;
    private int requestTimeoutSeconds;
}
