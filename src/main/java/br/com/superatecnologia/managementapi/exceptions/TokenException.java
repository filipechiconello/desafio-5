package br.com.superatecnologia.managementapi.exceptions;


import br.com.superatecnologia.managementapi.exceptions.enums.TokenEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TokenException extends PicPayException {

    private static final long serialVersionUID = -4589179341768493322L;

    public TokenException(TokenEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final TokenEnum error;
}
