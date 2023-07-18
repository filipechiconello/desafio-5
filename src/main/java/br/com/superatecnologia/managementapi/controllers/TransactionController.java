package br.com.superatecnologia.managementapi.controllers;

import br.com.superatecnologia.managementapi.dtos.requests.TransactionRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.TransactionResponseDTO;
import br.com.superatecnologia.managementapi.facades.TransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestHeader String token,
                                                                    @RequestBody TransactionRequestDTO transactionRequestDTO) {
        return new ResponseEntity<>(transactionFacade.createTransaction(token, transactionRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponseDTO>> findAll(@RequestParam(required = false, defaultValue = "1") Integer page) {
        return new ResponseEntity<>(transactionFacade.findAll(page), HttpStatus.OK);
    }
}