package com.acme.dbo;

import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.domain.Account;
import com.acme.dbo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Look ma, no Spring at all!
 */
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    /**
     * see also {@link org.mockito.MockitoAnnotations#openMocks} instead of {@link org.mockito.junit.jupiter.MockitoExtension}
     */
    @Mock private AccountRepository accountRepositoryStub;
    @InjectMocks private AccountService sut;

    @Test
    public void shouldGetNoAccountsWhenEmptyRepository() {
        when(accountRepositoryStub.findAll()).thenReturn(
                asList(new Account(2, new BigDecimal("2.22"))));

        assertTrue(sut.findAll().contains(
                new Account(2, new BigDecimal("2.22"))));
    }
}
