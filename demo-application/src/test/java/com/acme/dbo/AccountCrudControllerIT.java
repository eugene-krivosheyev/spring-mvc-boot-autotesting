package com.acme.dbo;

import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountCrudControllerIT {
    @MockBean AccountRepository accountRepositoryStub;
    @Autowired ObjectMapper mapper;
    @Autowired private MockMvc mockMvc;

    @Test
    public void shouldGetNoAccountsWhenNoCreated() throws Exception {
        mockMvc.perform(get("/api/account"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void shouldGetAccountWhenCreated() throws Exception {
        when(accountRepositoryStub
                .create(new Account(new BigDecimal("1.11"))))
                .thenReturn(new Account(1, new BigDecimal("1.11")));

        mockMvc.perform(post("/api/account")
                    .content(mapper.writeValueAsString(new Account(new BigDecimal("1.11"))))
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.amount").value(new BigDecimal("1.11")));
    }
}
