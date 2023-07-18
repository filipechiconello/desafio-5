package br.com.superatecnologia.managementapi.service.impl;

import br.com.superatecnologia.managementapi.entities.AccountEntity;
import br.com.superatecnologia.managementapi.repositories.AccountRepository;
import br.com.superatecnologia.managementapi.services.impl.AccountServiceImpl;
import br.com.superatecnologia.managementapi.utils.VerifyExistsInTheBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private VerifyExistsInTheBase verifyExistsInTheBase;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Before
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<AccountEntity> accounts = new ArrayList<>();
        accounts.add(new AccountEntity(1L, "John", "john@example.com", "password", new BigDecimal("100"), new ArrayList<>()));
        accounts.add(new AccountEntity(2L, "Alice", "alice@example.com", "password", new BigDecimal("200"), new ArrayList<>()));
        Mockito.when(accountRepository.findAll()).thenReturn(accounts);
        List<AccountEntity> result = accountService.findAll();
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Alice", result.get(1).getName());
        Mockito.verify(accountRepository).findAll();
    }

    @Test
    public void testFindById_ExistingId() {
        Long accountId = 1L;
        AccountEntity account = new AccountEntity(accountId, "John", "john@example.com", "password", new BigDecimal("100"), new ArrayList<>());
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        AccountEntity result = accountService.findById(accountId);
        assertNotNull(result);
        assertEquals("John", result.getName());
        Mockito.verify(accountRepository).findById(accountId);
    }

    @Test
    public void testGetByEmail_ExistingEmail() {

        String email = "john@example.com";
        AccountEntity account = new AccountEntity(1L, "John", email, "password", new BigDecimal("100"), new ArrayList<>());
        Mockito.when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));
        Optional<AccountEntity> result = accountService.getByEmail(email);
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        Mockito.verify(accountRepository).findByEmail(email);
    }

    @Test
    public void testGetByEmail_NonExistingEmail() {
        String email = "john@example.com";
        Mockito.when(accountRepository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<AccountEntity> result = accountService.getByEmail(email);
        assertFalse(result.isPresent());
        Mockito.verify(accountRepository).findByEmail(email);
    }

    @Test
    public void testFindByName_ExistingName() {
        String name = "John";
        AccountEntity account = new AccountEntity(1L, name, "john@example.com", "password", new BigDecimal("100"), new ArrayList<>());
        Mockito.when(accountRepository.findByName(name)).thenReturn(account);
        AccountEntity result = accountService.findByName(name);
        assertNotNull(result);
        assertEquals(name, result.getName());
        Mockito.verify(accountRepository).findByName(name);
    }

    @Test
    public void testFindByName_NonExistingName() {
        String name = "John";
        Mockito.when(accountRepository.findByName(name)).thenReturn(null);
        AccountEntity result = accountService.findByName(name);
        assertNull(result);
        Mockito.verify(accountRepository).findByName(name);
    }

    @Test
    public void testDeleteById_ExistingId() {
        Long accountId = 1L;
        AccountEntity account = new AccountEntity(accountId, "John", "john@example.com", "password", new BigDecimal("100"), new ArrayList<>());
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        accountService.deleteById(accountId);
        Mockito.verify(accountRepository).findById(accountId);
        Mockito.verify(accountRepository).deleteById(accountId);
    }

    @Test
    public void testDeleteById_NonExistingId_ThrowsException() {
        Long accountId = 1L;
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.empty());
    }
}
