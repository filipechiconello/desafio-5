package br.com.superatecnologia.managementapi.mappers;


import br.com.superatecnologia.managementapi.dtos.requests.AccountRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.AccountResponseDTO;
import br.com.superatecnologia.managementapi.entities.AccountEntity;
import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.exceptions.enums.UsersEnum;
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
public class UsersMappers {

    @Autowired
    private final ModelMapper mapper;

    public AccountEntity toEntity(AccountRequestDTO request) {
        return mapper.map(request, AccountEntity.class);
    }

    public AccountResponseDTO toDto(AccountEntity entity) {
        return mapper.map(entity, AccountResponseDTO.class);
    }

    public AccountRequestDTO responseToRequest(AccountResponseDTO response) {
        return mapper.map(response, AccountRequestDTO.class);
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
