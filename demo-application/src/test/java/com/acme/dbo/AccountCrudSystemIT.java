package com.acme.dbo;

import com.acme.dbo.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountCrudSystemIT {
    @Autowired private AccountController accountController;

    @Test
    public void shouldGetNoAccountsWhenNoCreated() {
        assertTrue(accountController.findAll().isEmpty());
    }
}
