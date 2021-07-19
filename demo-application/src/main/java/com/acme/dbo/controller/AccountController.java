package com.acme.dbo.controller;

import com.acme.dbo.domain.Account;
import com.acme.dbo.service.AccountService;

import java.util.Collection;

public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    public Account create(Account accountData) {
        return service.create(accountData);
    }

    public Account findById(Integer id) throws AccountNotFoundException {
        Account account = service.findById(id);
        if (account == null) throw new AccountNotFoundException(id);
        return account;
    }

    public Collection<Account> findAll() {
        return service.findAll();
    }
}
