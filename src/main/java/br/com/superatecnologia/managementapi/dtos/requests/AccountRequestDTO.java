package br.com.superatecnologia.managementapi.dtos.requests;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {

    private String name;
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#@$%&])(?=.*[0-9])(?=.*[a-z]).{6,15}$", message = "password.invalid")
    private String password;

    private String confirmPassword;
    private BigDecimal balance;
    private int otp;
}