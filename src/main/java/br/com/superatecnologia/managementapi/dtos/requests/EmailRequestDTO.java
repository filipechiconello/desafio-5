package br.com.superatecnologia.managementapi.dtos.requests;

import lombok.Data;

import java.util.List;

@Data
public class EmailRequestDTO {

    private String from;
    private List<String> to;
    private String subject;
    private String content;
}
