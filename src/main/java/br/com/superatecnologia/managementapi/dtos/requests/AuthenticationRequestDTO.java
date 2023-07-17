package br.com.superatecnologia.managementapi.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationRequestDTO {

    private String email;
    private String password;
}
