package com.dependency.mscore.grpc.dto.request;

import com.dependency.mscore.grpc.config.GenericServiceGrpc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InternalRequestDTO<P> extends GenericRequestDTO<P> implements Serializable {
    private static final long serialVersionUID = -2334315079886883437L;
    public GenericServiceGrpc.GenericServiceBlockingStub blockingStub;
    public GenericServiceGrpc.GenericServiceStub asyncStub;
}
