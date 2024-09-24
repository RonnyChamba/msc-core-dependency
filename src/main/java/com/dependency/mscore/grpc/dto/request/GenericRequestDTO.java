package com.dependency.mscore.grpc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GenericRequestDTO<P> implements Serializable {

    private static final long serialVersionUID = 2334315079886883437L;
    private String transactionId;
    private String option;
    private String origin;
    //    private EnumRequestType requestType;
    private String userRequest;
    private String ipRequest;
    private P payload;
}
