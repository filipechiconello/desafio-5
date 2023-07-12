package br.com.superatecnologia.managementapi.services;

import br.com.superatecnologia.managementapi.entities.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountEntity> findAll();

    AccountEntity save(AccountEntity accountEntity);

    AccountEntity findById(Long id);

    AccountEntity updateById(Long id, AccountEntity accountEntity);

    Optional<AccountEntity> getByEmail(String email);

    AccountEntity findByName(String name);

    void deleteById(Long id);
}
