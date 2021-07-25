package com.acme.dbo;

import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import com.acme.dbo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(AccountService.class)
public class AccountServiceIntegrationIT {
    @Autowired AccountService sut;
    @Autowired MockRestServiceServer externalServiceStub;
    @MockBean AccountRepository accountRepositoryStub;

    @Test
    public void shouldGetNewAccountsOnlyWhenExternalServiceIsEmpty() {
        when(accountRepositoryStub.findAll()).thenReturn(Arrays.asList(new Account(1, new BigDecimal("1.11"))));
        externalServiceStub
                .expect(requestTo("/old-api/account"))
                .andRespond(withSuccess("[]", MediaType.APPLICATION_JSON));

        List<Account> accounts = new ArrayList<>();
        Iterable<Account> accountsIterator = sut.findAll();
        accountsIterator.forEach(accounts::add);

        assertEquals(1, accounts.size());
        assertTrue(accounts.contains(new Account(1, new BigDecimal("1.11"))));
    }

    @Test
    public void shouldGetTotalAccountsWhenRepositoryIsNotEmptyAndLegacyServiceAvailable() {
        when(accountRepositoryStub.findAll()).thenReturn(Arrays.asList(new Account(1, new BigDecimal("1.11"))));
        externalServiceStub
                .expect(requestTo("/old-api/account"))
                .andRespond(withSuccess("[{ \"id\":\"10\",\"amount\":10.10}]", MediaType.APPLICATION_JSON));

        List<Account> accounts = new ArrayList<>();
        Iterable<Account> accountsIterator = sut.findAll();
        accountsIterator.forEach(accounts::add);

        assertEquals(2, accounts.size());
        assertTrue(accounts.contains(new Account(1, new BigDecimal("1.11"))));
        assertTrue(accounts.contains(new Account(10, new BigDecimal("10.10"))));
    }

    @Test
    public void shouldGetNoAdditionalAccountsWhenExternalLegacyServiceNotAvailable() {
        when(accountRepositoryStub.findAll()).thenReturn(Arrays.asList(new Account(1, new BigDecimal("1.11"))));
        externalServiceStub
                .expect(requestTo("/old-api/account"))
                .andRespond(withServerError());

        List<Account> accounts = new ArrayList<>();
        Iterable<Account> accountsIterator = sut.findAll();
        accountsIterator.forEach(accounts::add);

        assertEquals(1, accounts.size());
        assertTrue(accounts.contains(new Account(1, new BigDecimal("1.11"))));
    }
}
