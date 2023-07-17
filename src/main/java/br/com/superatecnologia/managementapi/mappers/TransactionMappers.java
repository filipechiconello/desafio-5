package br.com.superatecnologia.managementapi.mappers;


import br.com.superatecnologia.managementapi.dtos.requests.TransactionRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.TransactionResponseDTO;
import br.com.superatecnologia.managementapi.entities.TransactionEntity;
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
public class TransactionMappers {

    @Autowired
    private final ModelMapper mapper;

    public TransactionEntity toEntity(TransactionRequestDTO request) {
        return mapper.map(request, TransactionEntity.class);
    }

    public TransactionResponseDTO toDto(TransactionEntity entity) {
        return mapper.map(entity, TransactionResponseDTO.class);
    }

    public TransactionRequestDTO responseToRequest(TransactionResponseDTO response) {
        return mapper.map(response, TransactionRequestDTO.class);
    }

    public List<TransactionResponseDTO> toDtoList(Iterable<TransactionEntity> list) {
        List<TransactionEntity> result = new ArrayList<>();
        list.forEach(result::add);

        return result.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
