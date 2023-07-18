package br.com.superatecnologia.managementapi.dtos.requests;

import br.com.superatecnologia.managementapi.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    private BigDecimal value;
    private TypeEnum type;
    private String payerName;
    private String receiverName;
}
