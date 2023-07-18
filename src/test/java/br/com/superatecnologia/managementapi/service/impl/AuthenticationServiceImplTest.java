package br.com.superatecnologia.managementapi.service.impl;

import br.com.superatecnologia.managementapi.dtos.requests.AuthenticationRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.AuthenticationResponseDTO;
import br.com.superatecnologia.managementapi.entities.AccountEntity;
import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.repositories.AccountRepository;
import br.com.superatecnologia.managementapi.services.impl.AuthenticationServiceImpl;
import br.com.superatecnologia.managementapi.utils.JwtUtil;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class AuthenticationServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateToken_ValidCredentials() {
        // Mocking data
        String email = "test@example.com";
        String password = "password123";
        String encodedPassword = "encodedPassword";
        Long userId = 1L;
        String name = "John Doe";
        AccountEntity user = new AccountEntity(userId);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setName(name);
        AuthenticationRequestDTO authenticationRequest = new AuthenticationRequestDTO(email, password);
        String accessToken = "accessToken";
        Mockito.when(accountRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        Mockito.when(jwtUtil.generateTokenJwt(userId, email, name)).thenReturn(accessToken);
        AuthenticationResponseDTO result = authenticationService.generateToken(authenticationRequest);
        assertNotNull(result);
        assertEquals(accessToken, result.getToken());
        Mockito.verify(accountRepository).findByEmail(email);
        Mockito.verify(passwordEncoder).matches(password, encodedPassword);
        Mockito.verify(jwtUtil).generateTokenJwt(userId, email, name);
        Mockito.verify(jwtUtil).validateToken(accessToken);
    }

    @Test
    public void testGenerateToken_InvalidEmail_ThrowsException() {
        String email = "test@example.com";
        String password = "password123";
        AuthenticationRequestDTO authenticationRequest = new AuthenticationRequestDTO(email, password);
        Mockito.when(accountRepository.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(AccountException.class, () -> authenticationService.generateToken(authenticationRequest));
        Mockito.verify(accountRepository).findByEmail(email);
        Mockito.verifyNoInteractions(passwordEncoder);
        Mockito.verifyNoInteractions(jwtUtil);
    }

    @Test
    public void testGenerateToken_InvalidPassword_ThrowsException() {
        String email = "test@example.com";
        String password = "password123";
        String encodedPassword = "encodedPassword";
        Long userId = 1L;
        AccountEntity user = new AccountEntity(userId);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        AuthenticationRequestDTO authenticationRequest = new AuthenticationRequestDTO(email, password);
        Mockito.when(accountRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

    }
}