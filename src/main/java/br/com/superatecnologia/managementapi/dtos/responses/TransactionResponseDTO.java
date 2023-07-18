package br.com.superatecnologia.managementapi.dtos.responses;

import br.com.superatecnologia.managementapi.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    private String message;
    private BigDecimal value;
    private TypeEnum type;
    private String payerName;
    private String receiverName;
}
