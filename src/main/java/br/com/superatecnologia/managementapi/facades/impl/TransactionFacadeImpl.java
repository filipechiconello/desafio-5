package br.com.superatecnologia.managementapi.facades.impl;

import br.com.superatecnologia.managementapi.dtos.requests.TransactionRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.TransactionResponseDTO;
import br.com.superatecnologia.managementapi.facades.TransactionFacade;
import br.com.superatecnologia.managementapi.mappers.TransactionMappers;
import br.com.superatecnologia.managementapi.services.TransactionService;
import br.com.superatecnologia.managementapi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class TransactionFacadeImpl implements TransactionFacade {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMappers mappers;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public TransactionResponseDTO createTransaction(String token, TransactionRequestDTO transactionRequestDTO) {
        jwtUtil.validateToken(token);
        return mappers.toDto(transactionService.save(mappers.toEntity(transactionRequestDTO)));
    }

    @Override
    public Page<TransactionResponseDTO> findAll(Integer page) {
        return new PageImpl<>(mappers.toDtoList(transactionService.findAll(page)));
    }
}