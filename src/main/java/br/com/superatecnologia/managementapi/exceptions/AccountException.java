package br.com.superatecnologia.managementapi.exceptions;

import br.com.superatecnologia.managementapi.exceptions.enums.UsersEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class AccountException extends PicPayException {

    private static final long serialVersionUID = -4589179341768493322L;

    public AccountException(UsersEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final UsersEnum error;
}
