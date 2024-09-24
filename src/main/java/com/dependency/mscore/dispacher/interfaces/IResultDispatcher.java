package com.dependency.mscore.dispacher.interfaces;

import com.dependency.mscore.grpc.dto.request.GenericRequestDTO;
import com.dependency.mscore.grpc.dto.response.GenericResponseDTO;

public interface IResultDispatcher {
    <T, R> void registerSimpleResultHandler(Class<T> paramClass, IHandlerMethod<T, R> handler, Object config);
    <T, R> GenericResponseDTO<R> dispatch(GenericRequestDTO<T> request);
}
