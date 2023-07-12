package br.com.superatecnologia.managementapi.exceptions;


import br.com.superatecnologia.managementapi.exceptions.enums.OtpEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class OtpException extends PicPayException {

    private static final long serialVersionUID = -4589179341768493322L;

    public OtpException(OtpEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final OtpEnum error;
}
