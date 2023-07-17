package br.com.superatecnologia.managementapi.mappers;


import br.com.superatecnologia.managementapi.dtos.requests.AccountRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.AccountResponseDTO;
import br.com.superatecnologia.managementapi.entities.AccountEntity;
import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.exceptions.enums.UsersEnum;
import br.com.superatecnologia.managementapi.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountMappers {

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private TransactionRepository transactionRepository;

    public AccountEntity toEntity(AccountRequestDTO request) {
        AccountEntity accountEntity = mapper.map(request, AccountEntity.class);
        accountEntity.setTransactions(new ArrayList<>());

        return accountEntity;
    }

    public AccountResponseDTO toDto(AccountEntity entity) {
        List<Long> transactionsIds = new ArrayList<>();
        AccountResponseDTO accountResponseDTO = mapper.map(entity, AccountResponseDTO.class);

        transactionRepository.findAll().forEach(transactionEntity -> {
            if (transactionEntity.getPayer().getName().equals(entity.getName())
                    || transactionEntity.getReceiver().getName().equals(entity.getName())) {
                transactionsIds.add(transactionEntity.getId());
            }
        });

        accountResponseDTO.setTransactions(transactionsIds);

        return accountResponseDTO;
    }

    public List<AccountResponseDTO> toDtoList(Iterable<AccountEntity> list) {
        List<AccountEntity> result = new ArrayList<>();
        list.forEach(result::add);

        return result.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void verifyPasswordAndPasswordConfirmation(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            log.warn("passwords do not match");
            throw new AccountException(UsersEnum.PASSWORD_DO_NOT_MATCH);
        }
    }
}
