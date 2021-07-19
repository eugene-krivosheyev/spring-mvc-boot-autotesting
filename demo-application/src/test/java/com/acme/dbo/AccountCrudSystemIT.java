package com.acme.dbo;

import com.acme.dbo.controller.AccountController;
import com.acme.dbo.dao.MapBackedAccountRepository;
import com.acme.dbo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountCrudSystemIT {
    private AccountController accountController;

    @BeforeEach
    public void initApplicationContext() {
        accountController = new AccountController(new AccountService(new MapBackedAccountRepository(5)));
    }

    @Test
    public void shouldGetNoAccountsWhenNoCreated() {
        assertTrue(accountController.findAll().isEmpty());
    }
}
