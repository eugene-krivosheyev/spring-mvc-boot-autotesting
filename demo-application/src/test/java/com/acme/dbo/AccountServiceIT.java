package com.acme.dbo;

import com.acme.dbo.controller.AccountNotFoundException;
import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import com.acme.dbo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Profile("test")
public class AccountServiceIT {
    @Autowired private AccountRepository accountRepository;
    @Autowired private AccountService accountService;

    @Test
    public void shouldFindByIdWhenExists() throws AccountNotFoundException {
        accountRepository.save(new Account(new BigDecimal("1.11")));

        assertEquals(
                new Account(1, new BigDecimal("1.11")),
                accountService.findById(1));
    }
}
