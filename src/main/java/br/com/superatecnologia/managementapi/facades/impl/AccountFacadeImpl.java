package br.com.superatecnologia.managementapi.facades.impl;


import br.com.superatecnologia.managementapi.dtos.requests.AccountRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.AccountResponseDTO;
import br.com.superatecnologia.managementapi.entities.OtpEntity;
import br.com.superatecnologia.managementapi.exceptions.OtpException;
import br.com.superatecnologia.managementapi.exceptions.enums.OtpEnum;
import br.com.superatecnologia.managementapi.facades.AccountFacade;
import br.com.superatecnologia.managementapi.mappers.AccountMappers;
import br.com.superatecnologia.managementapi.services.AccountService;
import br.com.superatecnologia.managementapi.services.OTPService;
import br.com.superatecnologia.managementapi.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;


@Component
@Slf4j
public class AccountFacadeImpl implements AccountFacade {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AccountMappers mappers;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public List<AccountResponseDTO> findAll(String token) {
        jwtUtil.validateToken(token);
        return mappers.toDtoList(accountService.findAll());
    }

    @Override
    public AccountResponseDTO save(AccountRequestDTO accountRequestDTO) {
        mappers.verifyPasswordAndPasswordConfirmation(accountRequestDTO.getPassword(), accountRequestDTO.getConfirmPassword());

        OtpEntity otpEntity = otpService.findCodeByEmail(accountRequestDTO.getEmail());
        if (otpEntity != null && accountRequestDTO.getOtp() != otpEntity.getCode()) {
            throw new OtpException(OtpEnum.OTP_INVALID);
        } else if (otpEntity == null) {
            throw new OtpException(OtpEnum.OTP_DO_NOT_EXIST);
        } else if (Calendar.getInstance().getTime().after(otpEntity.getExpiration())) {
            otpService.remove(otpEntity.getId());
            throw new OtpException(OtpEnum.OTP_EXPIRED);
        }

        otpService.remove(otpEntity.getId());
        accountRequestDTO.setPassword(encoder.encode(accountRequestDTO.getPassword()));
        return mappers.toDto(accountService.save(mappers.toEntity(accountRequestDTO)));
    }

    @Override
    public AccountResponseDTO findById(String token) {
        jwtUtil.validateToken(token);
        return mappers.toDto(accountService.findById(jwtUtil.getAllClaims(token).getId()));
    }

    @Override
    public AccountResponseDTO updateById(Long id, AccountRequestDTO accountRequestDTO, String token) {
        jwtUtil.validateToken(token);
        return mappers.toDto(accountService.updateById(id, mappers.toEntity(accountRequestDTO)));
    }

    @Override
    public void deleteById(Long id, String token) {
        jwtUtil.validateToken(token);
        accountService.deleteById(id);
    }
}