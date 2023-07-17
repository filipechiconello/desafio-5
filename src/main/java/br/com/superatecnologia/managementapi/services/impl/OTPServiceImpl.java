package br.com.superatecnologia.managementapi.services.impl;

import br.com.superatecnologia.managementapi.entities.OtpEntity;
import br.com.superatecnologia.managementapi.repositories.OtpRepository;
import br.com.superatecnologia.managementapi.services.OTPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Slf4j
@Service
public class OTPServiceImpl implements OTPService {

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public int generateOtpCode(String email) {
        log.info("generating otp code to user - {}", email);
        int code = 100000 + new Random().nextInt(900000);

        saveOtp(email, code);
        return code;
    }

    @Override
    public void saveOtp(String email, int code) {
        log.info("saving new otp code to user - {}", email);
        Calendar expirationTime = Calendar.getInstance();
        expirationTime.setTime(new Date());
        expirationTime.add(Calendar.MINUTE, 5);

        otpRepository.save(new OtpEntity(code, email, expirationTime.getTime()));
    }

    @Override
    public OtpEntity findCodeByEmail(String email) {
        log.info("finding otp code to user - {}", email);
        return otpRepository.findByEmail(email);
    }

    @Override
    public void remove(Long id) {
        log.info("removing otp code");
        otpRepository.deleteById(id);
    }
}
