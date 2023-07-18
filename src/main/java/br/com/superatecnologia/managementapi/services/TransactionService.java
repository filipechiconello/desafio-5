package br.com.superatecnologia.managementapi.services;

import br.com.superatecnologia.managementapi.entities.TransactionEntity;

import java.util.List;

public interface TransactionService {

    List<TransactionEntity> findAll();

    TransactionEntity save(TransactionEntity transactionEntity);

    TransactionEntity findById(Long id);

    TransactionEntity updateById(Long id, TransactionEntity transactionEntity);

    void deleteById(Long id);
}