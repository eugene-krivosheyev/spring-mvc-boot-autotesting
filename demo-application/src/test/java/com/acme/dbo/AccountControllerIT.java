package com.acme.dbo;

import com.acme.dbo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Be careful of difference on @{@link org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest} and annotation and @{@link org.springframework.boot.test.context.SpringBootTest}
 */
@WebMvcTest
@ActiveProfiles("test")
public class AccountControllerIT {
    /**
     * Note mock's auto-reset with @MockBean: https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4#mocking-and-spying with {@link org.mockito.Mockito#reset}
     */
    @MockBean AccountService accountServiceStub;
    @Autowired MockMvc mockMvc;

    @Test
    public void shouldGetNoAccountsWhenNoCreated() throws Exception {
        when(accountServiceStub.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/account"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
