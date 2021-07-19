package com.acme.dbo;

import com.acme.dbo.config.Config;
import com.acme.dbo.controller.AccountController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountCrudSystemIT {
    private AnnotationConfigApplicationContext context;
    private AccountController accountController;

    @BeforeEach
    public void initApplicationContext() {
        context = new AnnotationConfigApplicationContext(Config.class);
        accountController = context.getBean(AccountController.class);
    }

    @AfterEach
    public void closeApplicationContext() {
        context.close();
    }

    @Test
    public void shouldGetNoAccountsWhenNoCreated() {
        assertTrue(accountController.findAll().isEmpty());
    }
}
