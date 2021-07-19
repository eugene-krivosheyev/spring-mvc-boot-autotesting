package com.acme.dbo.config;

import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.dao.MapBackedAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.acme.dbo")
public class Config {
    @Bean
    public AccountRepository accountRepository() {
        return new MapBackedAccountRepository(10);
    }
}
