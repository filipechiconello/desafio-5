package br.com.superatecnologia.managementapi.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponseDTO {

    private Long id;
    private String email;
    private String name;
}
