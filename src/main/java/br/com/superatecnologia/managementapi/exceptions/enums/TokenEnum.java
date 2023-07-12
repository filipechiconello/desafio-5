package br.com.superatecnologia.managementapi.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TokenEnum {

    INVALID_TOKEN("RGT_TKN_001", "Token inválido", 401),
    EXPIRED_EXCEPTION("RGT_TKN_002", "Token expirado", 401),
    REQUIRED_TOKEN_EXCEPTION("RGT_TKN_003", "Token obrigatório", 401),
    TOKEN_ALREADY_EXIST_TO_USER("RGT_TKN_005", "Token já existe para esse email", 400),
    ACCESS_DENIED("RGT_TKN_004", "Acesso negado", 403);

    private String code;
    private String message;
    private Integer statusCode;
}
