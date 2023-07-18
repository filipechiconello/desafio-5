package br.com.superatecnologia.managementapi.exceptions;

import br.com.superatecnologia.managementapi.exceptions.enums.TransactionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TransactionException extends PicPayException {

    private static final long serialVersionUID = -4589179341768493322L;

    public TransactionException(TransactionEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final TransactionEnum error;
}
