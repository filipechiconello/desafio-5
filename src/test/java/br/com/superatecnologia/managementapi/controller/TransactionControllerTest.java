package br.com.superatecnologia.managementapi.controller;

import br.com.superatecnologia.managementapi.controllers.TransactionController;
import br.com.superatecnologia.managementapi.dtos.requests.TransactionRequestDTO;
import br.com.superatecnologia.managementapi.enums.TypeEnum;
import br.com.superatecnologia.managementapi.facades.TransactionFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionFacade transactionFacade;

    private final String ROTA = "/api/v1/transaction";
    private final String TOKEN = "NSIDBEIOUBND123";

    @Test
    public void saveAccountMustReturnOk() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA).header("token", TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(transactionRequestDTO())))
                .andExpect(status().is2xxSuccessful());
    }

    private TransactionRequestDTO transactionRequestDTO() {
        return new TransactionRequestDTO(
                new BigDecimal("230000"),
                TypeEnum.DEPOSIT,
                "PayerName",
                "ReceiverName"
        );
    }
}