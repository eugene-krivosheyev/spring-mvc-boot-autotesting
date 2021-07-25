package com.acme.dbo;

import com.acme.dbo.config.TestConfig;
import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource("classpath:application-test.properties")
@WebAppConfiguration
public class AccountCrudControllerIT {
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired AccountRepository accountRepositoryStub;
    @Autowired ObjectMapper mapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldGetNoAccountsWhenNoCreated() throws Exception {
        mockMvc.perform(get("/api/account"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DirtiesContext
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
