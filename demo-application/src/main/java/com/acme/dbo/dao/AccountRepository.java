package com.acme.dbo.dao;

import java.util.Collection;

import com.acme.dbo.domain.Account;

public interface AccountRepository {
    Account create(Account accountData);
    Account findById(Integer id);
    Collection<Account> findAll();
}
