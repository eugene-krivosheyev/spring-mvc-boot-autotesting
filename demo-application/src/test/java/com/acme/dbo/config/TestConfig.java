package com.acme.dbo.config;

import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.util.Collection;

import static java.util.Collections.EMPTY_SET;

@Configuration
@Import(Config.class)
public class TestConfig {
    @Bean @Primary
    public AccountRepository accountRepositoryStub(@Value("${accounts.repo.init-capacity}") int initCapacity) {
        return new AccountRepositoryStub(initCapacity);
    }
}

class AccountRepositoryStub implements AccountRepository {
    private static final Logger log = LoggerFactory.getLogger(AccountRepositoryStub.class);
    private int initCapacity;

    public AccountRepositoryStub(int initCapacity) {
        this.initCapacity = initCapacity;
        log.debug("Created AccountRepositoryStub with initial capacity {}", initCapacity);
    }

    @Override
    public Account create(Account accountData) {
        return null;
    }

    @Override
    public Account findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Account> findAll() {
        return EMPTY_SET;
    }
}