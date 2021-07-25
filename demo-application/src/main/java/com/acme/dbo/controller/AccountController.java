package com.acme.dbo.controller;

import com.acme.dbo.domain.Account;
import com.acme.dbo.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public Account create(@RequestBody Account accountData) {
        System.out.println(">>>>> creating account " + accountData);
        return service.create(accountData);
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Integer id) throws AccountNotFoundException {
        Account account = service.findById(id);
        if (account == null) throw new AccountNotFoundException(id);
        return account;
    }

    @GetMapping
    public Collection<Account> findAll() {
        return service.findAll();
    }
}
