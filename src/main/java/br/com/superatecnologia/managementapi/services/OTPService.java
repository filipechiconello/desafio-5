package br.com.superatecnologia.managementapi.services;


import br.com.superatecnologia.managementapi.entities.OtpEntity;

public interface OTPService {

    int generateOtpCode(String email);

    void saveOtp(String email, int code);

    OtpEntity findCodeByEmail(String email);

    void remove(Long id);
}
