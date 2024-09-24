package com.dependency.mscore.grpc.communication;

import com.dependency.mscore.dispacher.interfaces.ICompleteDispatcher;
import com.dependency.mscore.grpc.component.GrpcUtilService;
import com.dependency.mscore.grpc.config.GenericRequest;
import com.dependency.mscore.grpc.config.GenericResponse;
import com.dependency.mscore.grpc.dto.request.GenericRequestDTO;
import com.dependency.mscore.grpc.dto.response.GenericResponseDTO;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MessageGrpcConsumer {

    private static final Logger LOGGER = LogManager.getLogger(MessageGrpcConsumer.class);

    public void consumeMessage(GenericRequest request,
                               StreamObserver<GenericResponse> responseObserver,
                               ICompleteDispatcher dispatcher) {
        GenericResponseDTO<String> genericResponseDTO = null;
        String option = "not definido";
        try {

            GenericRequestDTO<?> genericRequestDTO = (GenericRequestDTO) GrpcUtilService.generateClassFromAnyData(request.getData().getValue().toByteArray(), GenericRequestDTO.class);
            option = genericRequestDTO.getOption();
            LOGGER.info("Message received with option: {}", option);
            genericResponseDTO = dispatcher.dispatch(genericRequestDTO);
            LOGGER.info("Message prcessed successfully with option: {} ", option);

        } catch (Exception ex) {
            LOGGER.error("Error al procesar request GRPC: " + ex.getMessage());
            genericResponseDTO = GenericResponseDTO.<String>builder()
                    .code(500)
                    .status("ERROR")
                    .message("Error al procesar solicitud GRPC  con optionn " + option).build();
        }
        GenericResponse genericResponse = GenericResponse.newBuilder().setData(GrpcUtilService.generateAnyData(genericResponseDTO, GenericResponseDTO.class)).build();
        responseObserver.onNext(genericResponse);
        responseObserver.onCompleted();

    }
}
