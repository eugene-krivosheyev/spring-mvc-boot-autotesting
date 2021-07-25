package com.acme.dbo.service;


import com.acme.dbo.controller.AccountNotFoundException;
import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accounts;

    public AccountService(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public Account create(Account accountData) {
        return accounts.save(accountData);

    }

    public Account findById(Integer id) throws AccountNotFoundException {
        return accounts.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public Iterable<Account> findAll() {
        return accounts.findAll();
    }
}
