package br.com.superatecnologia.managementapi.facades;

import br.com.superatecnologia.managementapi.dtos.requests.TransactionRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.TransactionResponseDTO;
import org.springframework.data.domain.Page;

public interface TransactionFacade {

    TransactionResponseDTO createTransaction(String token, TransactionRequestDTO transactionRequestDTO);

    Page<TransactionResponseDTO> findAll(Integer page);
}