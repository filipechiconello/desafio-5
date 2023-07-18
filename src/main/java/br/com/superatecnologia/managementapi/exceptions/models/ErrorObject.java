package br.com.superatecnologia.managementapi.exceptions.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorObject {

    private final String message;
    private final String field;
    private final Object parameter;
}
