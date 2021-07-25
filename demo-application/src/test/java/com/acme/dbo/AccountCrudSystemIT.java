package com.acme.dbo;

import com.acme.dbo.controller.AccountController;
import com.acme.dbo.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class AccountCrudSystemIT {
    @Autowired private AccountController accountController;

    @Test
    public void shouldGetNoAccountsWhenNoCreated() {
        List<Account> accounts = new ArrayList<>();
        Iterable<Account> accountsIterator = accountController.findAll();
        accountsIterator.forEach(accounts::add);

        assertTrue(accounts.isEmpty());
    }

    @Test
    public void shouldGetAccountWhenCreated() {
        accountController.create(new Account(new BigDecimal("1.11")));

        List<Account> accounts = new ArrayList<>();
        Iterable<Account> accountsIterator = accountController.findAll();
        accountsIterator.forEach(accounts::add);

        assertTrue(accounts.contains(new Account(1, new BigDecimal("1.11"))));
    }
}
