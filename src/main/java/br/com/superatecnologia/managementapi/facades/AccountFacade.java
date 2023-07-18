package br.com.superatecnologia.managementapi.facades;


import br.com.superatecnologia.managementapi.dtos.requests.AccountRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.AccountResponseDTO;

import java.util.List;

public interface AccountFacade {

    List<AccountResponseDTO> findAll(String token);

    AccountResponseDTO save(AccountRequestDTO accountRequestDTO);

    AccountResponseDTO findById(String token);

    AccountResponseDTO updateById(Long id, AccountRequestDTO accountRequestDTO, String token);

    void deleteById(Long id, String token);
}