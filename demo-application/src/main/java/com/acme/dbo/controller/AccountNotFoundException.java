package com.acme.dbo.controller;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(Integer id) {
        super(id.toString());
    }
}
