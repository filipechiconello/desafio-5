package br.com.superatecnologia.managementapi.dtos.responses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {

    private Long id;
    private String name;
    private String email;
    private int number;
    private BigDecimal balance;
}