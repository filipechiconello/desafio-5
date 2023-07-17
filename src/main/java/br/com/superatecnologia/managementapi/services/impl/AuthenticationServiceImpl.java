package br.com.superatecnologia.managementapi.services.impl;


import br.com.superatecnologia.managementapi.dtos.requests.AuthenticationRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.AuthenticationResponseDTO;
import br.com.superatecnologia.managementapi.entities.AccountEntity;
import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.exceptions.AuthenticationException;
import br.com.superatecnologia.managementapi.exceptions.enums.AuthenticationEnum;
import br.com.superatecnologia.managementapi.exceptions.enums.UsersEnum;
import br.com.superatecnologia.managementapi.repositories.AccountRepository;
import br.com.superatecnologia.managementapi.services.AuthenticationService;
import br.com.superatecnologia.managementapi.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthenticationResponseDTO generateToken(AuthenticationRequestDTO authenticationRequestDTO) {
        AccountEntity user = accountRepository.findByEmail(authenticationRequestDTO.getEmail()).orElseThrow(() -> new AccountException(UsersEnum.USER_NOT_FOUND));

        if (encoder.matches(authenticationRequestDTO.getPassword(), user.getPassword())) {
            log.info("checking username and password of user {}", authenticationRequestDTO.getEmail());
            String accessToken = jwtUtil.generateTokenJwt(user.getId(), authenticationRequestDTO.getEmail(), user.getName());
            jwtUtil.validateToken(accessToken);
            return new AuthenticationResponseDTO(accessToken);
        } else {
            log.warn("{} username or password is invalid", authenticationRequestDTO.getEmail());
            throw new AuthenticationException(AuthenticationEnum.USER_OR_PASSAWORD_IS_INVALID);
        }
    }
}