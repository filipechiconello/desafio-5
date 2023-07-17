package br.com.superatecnologia.managementapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private BigDecimal value;

    @OneToOne
    private AccountEntity payer;

    @OneToOne
    private AccountEntity receiver;

    public TransactionEntity(Long id) {
        this.id = id;
    }
}