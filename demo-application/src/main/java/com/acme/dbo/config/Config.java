package com.acme.dbo.config;

import com.acme.dbo.controller.AccountController;
import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.dao.MapBackedAccountRepository;
import com.acme.dbo.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public AccountRepository accountRepository() {
        return new MapBackedAccountRepository(10);
    }

    @Bean
    public AccountService accountService(AccountRepository accountRepository) {
        return new AccountService(accountRepository);
    }

    @Bean
    public AccountController accountController(AccountService accountService) {
        return new AccountController(accountService);
    }
}
