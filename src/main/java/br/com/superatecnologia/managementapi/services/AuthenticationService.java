package br.com.superatecnologia.managementapi.services;

import br.com.superatecnologia.managementapi.dtos.requests.AuthenticationRequestDTO;
import br.com.superatecnologia.managementapi.dtos.responses.AuthenticationResponseDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO generateToken(AuthenticationRequestDTO authenticationRequestDTO);
}
