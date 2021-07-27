package com.acme.dbo;

import com.acme.dbo.config.Config;
import com.acme.dbo.controller.AccountController;
import com.acme.dbo.controller.AccountNotFoundException;
import com.acme.dbo.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * see {@link org.springframework.test.context.junit.jupiter.SpringJUnitConfig} annotation
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
public class AccountCrudSystemIT {
    @Autowired private AccountController accountController;

    @Test
    public void shouldGetNoAccountsWhenNoCreated() {
        assertTrue(accountController.findAll().isEmpty());
    }

    @Test
    public void shouldGetAccountWhenCreated(){
        Account dummyAccount = new Account(1, new BigDecimal(1));
        accountController.create(dummyAccount);
        assertFalse(accountController.findAll().isEmpty());
    }

    @Test
    public void shouldThrowsExceptionWhenAccountNull() throws AccountNotFoundException {
        Integer dummyId = 1;
        assertThrows(AccountNotFoundException.class, (Executable) accountController.findById(dummyId));
    }

    @Test
    public void shouldGetAccountByIdWhenCreated() throws AccountNotFoundException {
        Account dummyAccount = new Account(1, new BigDecimal(1));
        accountController.create(dummyAccount);
        assertEquals(dummyAccount, accountController.findById(dummyAccount.getId()));
    }

}
