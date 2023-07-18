package br.com.superatecnologia.managementapi.services.impl;

import br.com.superatecnologia.managementapi.entities.TransactionEntity;
import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.exceptions.enums.UsersEnum;
import br.com.superatecnologia.managementapi.repositories.TransactionRepository;
import br.com.superatecnologia.managementapi.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionEntity> findAll() {
        log.info("listing all transactions");
        return transactionRepository.findAll();
    }

    @Override
    public TransactionEntity save(TransactionEntity transactionEntity) {
        log.info("creating new transaction - {}", transactionEntity);
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public TransactionEntity findById(Long id) {
        log.info("listing transaction by id - {}", id);
        return transactionRepository.findById(id).orElseThrow(() -> new AccountException(UsersEnum.USER_NOT_FOUND));
    }

    @Override
    public TransactionEntity updateById(Long id, TransactionEntity transactionEntity) {
        log.info("updating transaction by id - {} - new values - {}", id, transactionEntity);
        findById(id);
        transactionEntity.setId(id);
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleting transaction by id - {}", id);
        findById(id);
        transactionRepository.deleteById(id);
    }
}