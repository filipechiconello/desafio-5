package br.com.superatecnologia.managementapi.mappers;

import br.com.superatecnologia.managementapi.dtos.requests.TransactionRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.TransactionResponseDTO;
import br.com.superatecnologia.managementapi.entities.AccountEntity;
import br.com.superatecnologia.managementapi.entities.TransactionEntity;
import br.com.superatecnologia.managementapi.exceptions.AccountException;
import br.com.superatecnologia.managementapi.exceptions.enums.UsersEnum;
import br.com.superatecnologia.managementapi.services.AccountService;
import br.com.superatecnologia.managementapi.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionMappers {

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtUtil jwtUtil;

    public TransactionEntity toEntity(TransactionRequestDTO request) {
        TransactionEntity transactionEntity = mapper.map(request, TransactionEntity.class);
        transactionEntity.setDate(LocalDateTime.now());
        List<Long> transactionsIdList = new ArrayList<>();

        AccountEntity payer = accountService.findByName(request.getPayerName());
        AccountEntity receiver = accountService.findByName(request.getReceiverName());
        transactionsIdList.add(transactionEntity.getId());

        if (payer.getBalance().doubleValue() <= request.getValue().doubleValue()) {
            throw new AccountException(UsersEnum.BALANCE_NOT_AVAILABLE);
        } else {
            payer.setBalance(payer.getBalance().subtract(request.getValue()));
            payer.getTransactions().add(transactionEntity.getId());
            transactionEntity.setPayer(accountService.updateById(payer.getId(), payer));
        }

        receiver.setBalance(receiver.getBalance().add(request.getValue()));
        receiver.getTransactions().add(transactionEntity.getId());
        transactionEntity.setReceiver(accountService.updateById(receiver.getId(), receiver));

        return transactionEntity;
    }

    public TransactionResponseDTO toDto(TransactionEntity entity) {
        TransactionResponseDTO transactionResponseDTO = mapper.map(entity, TransactionResponseDTO.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        transactionResponseDTO.setMessage("Valor de transferencia: R$ " + entity.getValue() + " na data de " + formatter.format(entity.getDate()));

        AccountEntity payer = accountService.findByName(entity.getPayer().getName());
        AccountEntity receiver = accountService.findByName(entity.getReceiver().getName());
        transactionResponseDTO.setPayerName(payer.getName());
        transactionResponseDTO.setReceiverName(receiver.getName());

        return transactionResponseDTO;
    }

    public List<TransactionResponseDTO> toDtoList(Iterable<TransactionEntity> list) {
        List<TransactionEntity> result = new ArrayList<>();
        list.forEach(result::add);

        return result.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
