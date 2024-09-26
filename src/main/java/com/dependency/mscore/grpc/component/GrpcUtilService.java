package com.dependency.mscore.grpc.component;

import com.dependency.mscore.grpc.config.GenericRequest;
import com.dependency.mscore.grpc.config.GenericResponse;
import com.dependency.mscore.grpc.config.GenericServiceGrpc;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GrpcUtilService {

    private static final Logger LOGGER = LogManager.getLogger(GrpcUtilService.class);
    static ObjectMapper objectMapper;

    public static <T, U> U sendCommunication(GenericServiceGrpc.GenericServiceBlockingStub blockingStub,
                                             T data,
                                             Class<T> inputClass,
                                             Class<U> ouputClass) {


        GenericResponse genericResponse = blockingStub.call(
                GenericRequest.newBuilder()
                        .setData(generateAnyData(data, inputClass))
                        .build());
        return generateClassFromAnyData(genericResponse.getData().getValue().toByteArray(), ouputClass);
    }

    public static <T> void sendCommunicationAsync(GenericServiceGrpc.GenericServiceStub blockingStub,
                                                  T data,
                                                  Class<T> inputClass,
                                                  StreamObserver<GenericResponse> responseObserver) {

        blockingStub.call(
                GenericRequest.newBuilder()
                        .setData(generateAnyData(data, inputClass))
                        .build(), responseObserver);
    }


    public static <T, U> U generateClassFromAnyData(byte[] genericByteArray, Class<T> classType) {
        objectMapper = new ObjectMapper();

        try {
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return (U) objectMapper.readValue(genericByteArray, classType);
        } catch (IOException e) {
            LOGGER.error("Error generating Generic Class from gRPC byteArray (ByteString)", e);
        }

        return null;
    }

    public static <T> Any.Builder generateAnyData(T data, Class<T> clazz) {
        objectMapper = new ObjectMapper();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.writeValue(byteArrayOutputStream, data);
            return Any.newBuilder().setTypeUrl(clazz.getCanonicalName()).setValue(ByteString.copyFrom(byteArrayOutputStream.toByteArray()));
        } catch (IOException var4) {
            IOException e = var4;
            LOGGER.error("Error generating Any data Type for gRPC", e);

            return null;
        }
    }
}
