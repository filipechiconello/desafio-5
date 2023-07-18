package br.com.superatecnologia.managementapi.controllers;

import br.com.superatecnologia.managementapi.dtos.requests.AccountRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.AccountResponseDTO;
import br.com.superatecnologia.managementapi.facades.AccountFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountFacade accountFacade;

    @Operation(summary = "creating new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "creating new account successfully"),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping
    public ResponseEntity<AccountResponseDTO> save(@RequestBody AccountRequestDTO accountRequestDTO) {
        return new ResponseEntity<>(accountFacade.save(accountRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> findAll(@RequestHeader String token) {
        return new ResponseEntity<>(accountFacade.findAll(token), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<AccountResponseDTO> findById(@RequestHeader String token) {
        return new ResponseEntity<>(accountFacade.findById(token), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateById(@PathVariable Long id,
                                                         @RequestBody AccountRequestDTO accountRequestDTO,
                                                         @RequestHeader String token) {
        return new ResponseEntity<>(accountFacade.updateById(id, accountRequestDTO, token), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id,
                                           @RequestHeader String token) {
        accountFacade.deleteById(id, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}