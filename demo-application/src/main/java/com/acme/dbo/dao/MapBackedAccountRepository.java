package com.acme.dbo.dao;

import com.acme.dbo.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapBackedAccountRepository implements AccountRepository {
    private static final Logger log = LoggerFactory.getLogger(MapBackedAccountRepository.class);
    private final Map<Integer, Account> accounts ;

    public MapBackedAccountRepository(int initialCapacity) {
        accounts = new HashMap<>(initialCapacity);
        log.debug("Created MapBackedAccountRepository with initial capacity {}", initialCapacity);
    }

    @Override
    public Account create(Account accountData) {
        Account newAccount = new Account(
            accounts.isEmpty() ? 0 : Collections.max(accounts.keySet()) + 1,
            accountData.getAmount()
        );

        accounts.put(newAccount.getId(), newAccount);
        return newAccount;
    }

    @Override
    public Account findById(Integer id) {
        return accounts.get(id);
    }

    @Override
    public Collection<Account> findAll() {
        return accounts.values();
    }
}
