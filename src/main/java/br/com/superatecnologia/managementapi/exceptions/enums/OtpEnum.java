package br.com.superatecnologia.managementapi.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OtpEnum {

    OTP_CODE_ALREADY_EXIST("OTP_CODE_ALREADY_EXIST", "otp code already exist to this email", 409),
    OTP_INVALID("OTP_INVALID", "otp code invalid", 400),
    OTP_DO_NOT_EXIST("OTP_DO_NOT_EXIST", "otp code do not exist", 400),
    OTP_EXPIRED("OTP_EXPIRED", "otp code expired", 400);

    private String code;
    private String message;
    private Integer statusCode;
}
