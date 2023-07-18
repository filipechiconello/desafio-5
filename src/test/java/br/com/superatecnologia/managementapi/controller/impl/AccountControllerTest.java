package br.com.superatecnologia.managementapi.controller.impl;

import br.com.superatecnologia.managementapi.controllers.AccountController;
import br.com.superatecnologia.managementapi.dtos.requests.AccountRequestDTO;
import br.com.superatecnologia.managementapi.facades.AccountFacade;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountFacade brokerFacade;

    private final String ROTA = "/api/v1/account";
    private final String ROTA_WITH_PARAM = "/api/v1/account/get";
    private final String TOKEN = "NSIDBEIOUBND123";

    @Test
    public void listAllAccountsMustReturnOk() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(ROTA).header("token", TOKEN))
                .andExpect(status().isOk());
    }

    @Test
    public void findAccountByIdMustReturnOk() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(ROTA_WITH_PARAM).header("token", TOKEN))
                .andExpect(status().isOk());
    }

    @Test
    public void updateAccountMustReturnOk() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put("/api/v1/account/1").header("token", TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(accountRequestDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void saveAccountMustReturnOk() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(accountRequestDTO())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteByIdMustReturnOk() throws Exception {
        mockMvc.perform(delete("/api/v1/account/1").header("token", TOKEN))
                .andExpect(status().isOk());
    }

    private AccountRequestDTO accountRequestDTO() {
        return new AccountRequestDTO(
                "name",
                "email",
                "Password123@",
                "Password123@",
                new BigDecimal("2000"),
                001122
        );
    }
}