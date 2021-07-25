package com.acme.dbo.service;


import com.acme.dbo.controller.AccountNotFoundException;
import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class AccountService {
    private static Logger log = LoggerFactory.getLogger(AccountService.class);
    private RestTemplate restTemplate;
    private final AccountRepository accounts;

    public AccountService(AccountRepository accounts, RestTemplateBuilder restTemplateBuilder) {
        this.accounts = accounts;
        restTemplate = restTemplateBuilder.build();
    }

    public Account create(Account accountData) {
        return accounts.save(accountData);

    }

    public Account findById(Integer id) throws AccountNotFoundException {
        return accounts.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public Iterable<Account> findAll() {
        Iterable<Account> accountsFound = accounts.findAll();

        Account[] legacyAccounts = {};
        try {
            legacyAccounts = restTemplate.getForObject("/old-api/account", Account[].class);
        } catch (Exception e) {
            log.error("Can't call legacy service. Nevermind.");
        }

        Collection<Account> accountsTotal = new ArrayList<>();
        Collections.addAll(accountsTotal, legacyAccounts);
        accountsFound.forEach(accountsTotal::add);

        return accountsTotal;
    }
}
