package br.com.superatecnologia.managementapi.service.impl;

import br.com.superatecnologia.managementapi.entities.AccountEntity;
import br.com.superatecnologia.managementapi.entities.TransactionEntity;
import br.com.superatecnologia.managementapi.enums.TypeEnum;
import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.repositories.TransactionRepository;
import br.com.superatecnologia.managementapi.services.impl.TransactionServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<TransactionEntity> transactions = new ArrayList<>();
        transactions.add(new TransactionEntity(1L, LocalDateTime.now(), new BigDecimal("100"), TypeEnum.DEPOSIT, new AccountEntity(1L), new AccountEntity(2L)));
        transactions.add(new TransactionEntity(2L, LocalDateTime.now(), new BigDecimal("200"), TypeEnum.DEPOSIT, new AccountEntity(3L), new AccountEntity(4L)));
        Mockito.when(transactionRepository.findAll()).thenReturn(transactions);
        List<TransactionEntity> result = transactionService.findAll();
        assertEquals(2, result.size());
        assertEquals(new BigDecimal("100"), result.get(0).getValue());
        assertEquals(new BigDecimal("200"), result.get(1).getValue());

        Mockito.verify(transactionRepository).findAll();
    }

    @Test
    public void testSave() {
        TransactionEntity transaction = new TransactionEntity(1L, LocalDateTime.now(), new BigDecimal("100"), TypeEnum.DEPOSIT, new AccountEntity(1L), new AccountEntity(2L));
        Mockito.when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        TransactionEntity result = transactionService.save(transaction);
        assertNotNull(result);
        assertEquals(new BigDecimal("100"), result.getValue());
        Mockito.verify(transactionRepository).save(transaction);
    }

    @Test
    public void testFindById_ExistingId() {
        Long transactionId = 1L;
        TransactionEntity transaction = new TransactionEntity(transactionId, LocalDateTime.now(), new BigDecimal("100"), TypeEnum.DEPOSIT, new AccountEntity(1L), new AccountEntity(2L));
        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        TransactionEntity result = transactionService.findById(transactionId);
        assertNotNull(result);
        assertEquals(new BigDecimal("100"), result.getValue());
        Mockito.verify(transactionRepository).findById(transactionId);
    }

    @Test
    public void testFindById_NonExistingId_ThrowsException() {
        Long transactionId = 1L;
        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());
        assertThrows(AccountException.class, () -> transactionService.findById(transactionId));
    }

    @Test
    public void testUpdateById_ExistingId() {
        Long transactionId = 1L;
        TransactionEntity existingTransaction = new TransactionEntity(transactionId, LocalDateTime.now(), new BigDecimal("100"), TypeEnum.DEPOSIT, new AccountEntity(1L), new AccountEntity(2L));
        TransactionEntity updatedTransaction = new TransactionEntity(transactionId, LocalDateTime.now(), new BigDecimal("200"), TypeEnum.DEPOSIT, new AccountEntity(3L), new AccountEntity(4L));
        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(existingTransaction));
        Mockito.when(transactionRepository.save(updatedTransaction)).thenReturn(updatedTransaction);
        TransactionEntity result = transactionService.updateById(transactionId, updatedTransaction);
        assertNotNull(result);
        assertEquals(new BigDecimal("200"), result.getValue());
        Mockito.verify(transactionRepository).findById(transactionId);
        Mockito.verify(transactionRepository).save(updatedTransaction);
    }

    @Test
    public void testUpdateById_NonExistingId_ThrowsException() {
        Long transactionId = 1L;
        TransactionEntity updatedTransaction = new TransactionEntity(transactionId, LocalDateTime.now(), new BigDecimal("200"), TypeEnum.DEPOSIT, new AccountEntity(3L), new AccountEntity(4L));
        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());
        assertThrows(AccountException.class, () -> transactionService.updateById(transactionId, updatedTransaction));
    }

    @Test
    public void testDeleteById_ExistingId() {
        Long transactionId = 1L;
        TransactionEntity existingTransaction = new TransactionEntity(transactionId, LocalDateTime.now(), new BigDecimal("100"), TypeEnum.DEPOSIT,
                new AccountEntity(1L), new AccountEntity(2L));
        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(existingTransaction));
        transactionService.deleteById(transactionId);
        Mockito.verify(transactionRepository).findById(transactionId);
        Mockito.verify(transactionRepository).deleteById(transactionId);
    }

    @Test
    public void testDeleteById_NonExistingId_ThrowsException() {
        Long transactionId = 1L;
        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());
        assertThrows(AccountException.class, () -> transactionService.deleteById(transactionId));
    }
}
