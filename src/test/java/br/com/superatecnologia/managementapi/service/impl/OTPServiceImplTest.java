package br.com.superatecnologia.managementapi.service.impl;

import br.com.superatecnologia.managementapi.entities.OtpEntity;
import br.com.superatecnologia.managementapi.repositories.OtpRepository;
import br.com.superatecnologia.managementapi.services.impl.OTPServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class OTPServiceImplTest {

    @Mock
    private OtpRepository otpRepository;

    @InjectMocks
    private OTPServiceImpl otpService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCodeByEmail() {
        String email = "test@example.com";
        OtpEntity otpEntity = new OtpEntity(1L, 123456, email, new Date());
        Mockito.when(otpRepository.findByEmail(email)).thenReturn(otpEntity);
        OtpEntity result = otpService.findCodeByEmail(email);
        assertNotNull(result);
        assertEquals(otpEntity, result);
        Mockito.verify(otpRepository).findByEmail(email);
    }

    @Test
    public void testRemove() {
        Long id = 1L;
        doNothing().when(otpRepository).deleteById(id);
        otpService.remove(id);
        verify(otpRepository).deleteById(id);
    }
}