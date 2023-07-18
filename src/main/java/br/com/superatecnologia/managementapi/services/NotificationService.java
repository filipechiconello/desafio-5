package br.com.superatecnologia.managementapi.services;


import br.com.superatecnologia.managementapi.dtos.requests.EmailRequestDTO;

public interface NotificationService {

    void notificationEmail(EmailRequestDTO emailRequestDTO);
}
