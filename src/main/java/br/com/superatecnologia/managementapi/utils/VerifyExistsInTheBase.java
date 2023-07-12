package br.com.superatecnologia.managementapi.utils;


import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.exceptions.enums.UsersEnum;
import br.com.superatecnologia.managementapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyExistsInTheBase {

    @Autowired
    private AccountRepository accountRepository;

    public void verifyUsers(String name, String email) {
        accountRepository.findAll().forEach(usersEntity -> {
            if (usersEntity.getName().equals(name)) {
                throw new AccountException(UsersEnum.NAME_ALREADY_EXISTS_IN_THE_BASE);
            }

            if (usersEntity.getEmail().equals(email)) {
                throw new AccountException(UsersEnum.EMAIL_ALREADY_EXISTS_IN_THE_BASE);
            }
        });
    }
}
