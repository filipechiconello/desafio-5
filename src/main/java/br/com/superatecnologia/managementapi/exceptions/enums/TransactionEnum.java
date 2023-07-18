package br.com.superatecnologia.managementapi.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TransactionEnum {

    NOT_AUTHORIZED("NOT_AUTHORIZED", "Not authorized", 400);

    private String code;
    private String message;
    private Integer statusCode;
}
