package com.acme.dbo;

import com.acme.dbo.controller.AccountController;
import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * see {@link org.springframework.test.context.junit.jupiter.SpringJUnitConfig} annotation
 */
@SpringBootTest
@ActiveProfiles("test")
public class AccountCrudIT {
    @MockBean private AccountRepository accountRepositoryStub;
    @Autowired private AccountController accountController;

    @Test
    public void shouldStoreAccountsWhenCreated() {
        when(accountRepositoryStub.findAll()).thenReturn(
                asList(new Account(3, new BigDecimal("3.33"))));

        List<Account> accounts = new ArrayList<>();
        Iterable<Account> accountsIterator = accountController.findAll();
        accountsIterator.forEach(accounts::add);

        assertTrue(accounts.contains(
                new Account(3, new BigDecimal("3.33"))));
    }
}
