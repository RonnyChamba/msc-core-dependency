package com.dependency.mscore.grpc.communication;

import com.dependency.mscore.grpc.component.GrpcUtilService;
import com.dependency.mscore.grpc.dto.request.GenericRequestDTO;
import com.dependency.mscore.grpc.dto.request.InternalRequestDTO;
import com.dependency.mscore.grpc.dto.response.GenericResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;

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


}
