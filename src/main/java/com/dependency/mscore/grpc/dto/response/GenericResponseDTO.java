package com.dependency.mscore.grpc.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 7384318079886883437L;

    private Integer code = 200;
    private String status = "OK";
    private String message = "The request was successful";
    private T payload;

}
