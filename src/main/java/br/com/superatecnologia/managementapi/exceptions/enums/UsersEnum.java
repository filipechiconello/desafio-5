package br.com.superatecnologia.managementapi.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UsersEnum {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", 404),
    NAME_ALREADY_EXISTS_IN_THE_BASE("NAME_ALREADY_EXISTS_IN_THE_BASE", "Name already exists in the base", 404),
    LAST_NAME_ALREADY_EXISTS_IN_THE_BASE("LAST_NAME_ALREADY_EXISTS_IN_THE_BASE", "Last name already exists in the base", 404),
    DOCUMENT_ALREADY_EXISTS_IN_THE_BASE("DOCUMENT_ALREADY_EXISTS_IN_THE_BASE", "Document already exists in the base", 404),
    EMAIL_ALREADY_EXISTS_IN_THE_BASE("EMAIL_ALREADY_EXISTS_IN_THE_BASE", "Email already exists in the base", 404),
    BALANCE_CANNOT_BE_LESS_THAN_ZERO("BALANCE_CANNOT_BE_LESS_THAN_ZERO", "Balance cannot be less than zero", 404),
    PASSWORD_DO_NOT_MATCH("RGT_USR_001", "Senhas não conferem", 400),
    USER_ALREADY_EXIST("RGT_USR_002", "Usuário já existe", 409),
    INVALID_OTP("RGT_USR_004", "O código OTP é inválido", 400),
    INVALID_PASSWORD("RGT_USR_005", "Senha atual inválida", 400),
    EMAIL_NOT_FOUND("EMAIL_NOT_FOUND", "Email não encontrado", 404);

    private String code;
    private String message;
    private Integer statusCode;
}