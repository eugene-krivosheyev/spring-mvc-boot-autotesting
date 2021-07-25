package com.acme.dbo;

import com.acme.dbo.config.Config;
import com.acme.dbo.config.TestConfig;
import com.acme.dbo.controller.AccountController;
import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * see {@link org.springframework.test.context.junit.jupiter.SpringJUnitConfig} annotation
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class AccountCrudIT {
    @Autowired private AccountRepository accountRepositoryStub;
    @Autowired private AccountController accountController;

    @Test
    public void shouldGetNoAccountsWhenNoCreated() {
        when(accountRepositoryStub.findAll()).thenReturn(
                asList(new Account(3, new BigDecimal("3.33"))));

        assertTrue(accountController.findAll().contains(
                new Account(3, new BigDecimal("3.33"))));
    }
}
