package br.com.superatecnologia.managementapi.services;

import br.com.superatecnologia.managementapi.entities.TransactionEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {

    Page<TransactionEntity> findAll(Integer page);

    TransactionEntity save(TransactionEntity transactionEntity);

    TransactionEntity findById(Long id);

    TransactionEntity updateById(Long id, TransactionEntity transactionEntity);

    void deleteById(Long id);
}