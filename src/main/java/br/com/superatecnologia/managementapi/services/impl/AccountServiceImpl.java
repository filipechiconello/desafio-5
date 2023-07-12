package br.com.superatecnologia.managementapi.services.impl;

import br.com.superatecnologia.managementapi.entities.AccountEntity;
import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.exceptions.enums.UsersEnum;
import br.com.superatecnologia.managementapi.repositories.AccountRepository;
import br.com.superatecnologia.managementapi.services.AccountService;
import br.com.superatecnologia.managementapi.utils.VerifyExistsInTheBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private VerifyExistsInTheBase verifyExistsInTheBase;

    @Override
    public List<AccountEntity> findAll() {
        log.info("listing all users");
        return accountRepository.findAll();
    }

    @Override
    public AccountEntity save(AccountEntity accountEntity) {
        if (accountEntity.getBalance().doubleValue() < 0) {
            throw new AccountException(UsersEnum.BALANCE_CANNOT_BE_LESS_THAN_ZERO);
        }
        verifyExistsInTheBase.verifyUsers(accountEntity.getName(), accountEntity.getEmail());
        AccountEntity result = accountRepository.save(accountEntity);
        result.setPassword("**********");
        log.info("registering a new user {}", result);
        return result;
    }

    @Override
    public AccountEntity findById(Long id) {
        log.info("listing user by id - {}", id);
        return accountRepository.findById(id).orElseThrow(() -> new AccountException(UsersEnum.USER_NOT_FOUND));
    }

    @Override
    public AccountEntity updateById(Long id, AccountEntity accountEntity) {
        log.info("updating user by id - {} - new values - {}", id, accountEntity);
        findById(id);
        accountEntity.setId(id);
        return accountRepository.save(accountEntity);
    }

    @Override
    public Optional<AccountEntity> getByEmail(String email) {
        log.info("listing user by email - {}", email);
        return accountRepository.findByEmail(email);
    }

    @Override
    public AccountEntity findByName(String name) {
        log.info("listing user by name - {}", name);
        return accountRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleting user by id - {}", id);
        findById(id);
        accountRepository.deleteById(id);
    }
}