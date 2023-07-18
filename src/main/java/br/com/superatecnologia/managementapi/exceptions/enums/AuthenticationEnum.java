package br.com.superatecnologia.managementapi.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AuthenticationEnum {

    USER_OR_PASSAWORD_IS_INVALID("USER_OR_PASSAWORD_IS_INVALID", "User or password is invalid", 400);

    private String code;
    private String message;
    private Integer statusCode;

}
