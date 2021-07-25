package com.acme.dbo;

import com.acme.dbo.config.TestConfig;
import com.acme.dbo.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * see {@link org.springframework.test.context.junit.jupiter.SpringJUnitConfig} annotation
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@WebAppConfiguration
@TestPropertySource("classpath:application-test.properties")
public class AccountCrudSystemIT {
    @Autowired private AccountController accountController;

    @Test
    public void shouldGetNoAccountsWhenNoCreated() {
        assertTrue(accountController.findAll().isEmpty());
    }
}
