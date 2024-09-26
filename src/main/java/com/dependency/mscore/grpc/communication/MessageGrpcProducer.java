package com.dependency.mscore.grpc.communication;

import com.dependency.mscore.grpc.component.GrpcUtilService;
import com.dependency.mscore.grpc.config.GenericResponse;
import com.dependency.mscore.grpc.dto.request.GenericRequestDTO;
import com.dependency.mscore.grpc.dto.request.InternalRequestDTO;
import com.dependency.mscore.grpc.dto.response.GenericResponseDTO;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageGrpcProducer {

    private static final Logger LOGGER = LogManager.getLogger(MessageGrpcConsumer.class);

    public GenericResponseDTO sendMessage(InternalRequestDTO requestDTO) {


        GenericResponseDTO response = null;

        try {
            if (Objects.isNull(requestDTO.getBlockingStub())) {
                System.out.println("BlockingStub is null");
                throw new Exception("BlockingStub is null");
            }
            response = (GenericResponseDTO) GrpcUtilService.sendCommunication(requestDTO.getBlockingStub(),
                    requestDTO, GenericRequestDTO.class, GenericResponseDTO.class);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return response;

    }


    public CompletableFuture<GenericResponseDTO> sendMessageAsync(InternalRequestDTO requestDTO) {


        CompletableFuture<GenericResponseDTO> futureResponse = new CompletableFuture<>();


        try {
            if (Objects.isNull(requestDTO.getBlockingStub())) {
                System.out.println("BlockingStub is null");
                throw new Exception("BlockingStub is null");
            }

            GrpcUtilService.sendCommunicationAsync(requestDTO.getAsyncStub(),
                    requestDTO, GenericRequestDTO.class,
                    new StreamObserver<GenericResponse>() {
                        @Override
                        public void onNext(GenericResponse genericResponse) {
                            GenericResponseDTO response = (GenericResponseDTO) GrpcUtilService.generateClassFromAnyData(genericResponse.getData().getValue().toByteArray(), GenericResponseDTO.class);
                            futureResponse.complete(response);
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            futureResponse.completeExceptionally(throwable);
                        }

                        @Override
                        public void onCompleted() {
                            LOGGER.info("Message completed");
                        }
                    });

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return futureResponse;
    }


}
