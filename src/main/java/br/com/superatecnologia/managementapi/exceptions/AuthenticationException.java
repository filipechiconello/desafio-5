package br.com.superatecnologia.managementapi.exceptions;

import br.com.superatecnologia.managementapi.exceptions.enums.AuthenticationEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class AuthenticationException extends PicPayException {

    private static final long serialVersionUID = -4589179341768493322L;

    public AuthenticationException(AuthenticationEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final AuthenticationEnum error;
}
