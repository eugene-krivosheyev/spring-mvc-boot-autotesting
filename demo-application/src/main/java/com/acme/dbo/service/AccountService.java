package com.acme.dbo.service;


import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;

import java.util.Collection;

public class AccountService {
    private final AccountRepository accounts;

    public AccountService(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public Account create(Account accountData) {
        return accounts.create(accountData);
    }

    public Account findById(Integer id) {
        return accounts.findById(id);
    }

    public Collection<Account> findAll() {
        return accounts.findAll();
    }
}
