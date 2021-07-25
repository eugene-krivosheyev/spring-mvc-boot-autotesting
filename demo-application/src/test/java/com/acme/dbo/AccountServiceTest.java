package com.acme.dbo;

import com.acme.dbo.dao.AccountRepository;
import com.acme.dbo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.emptyList;
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
    private AccountService sut;

    @BeforeEach
    public void initContext() {
        sut = new AccountService(accountRepositoryStub);
    }

    @Test
    public void shouldGetNoAccountsWhenEmptyRepository() {
        when(accountRepositoryStub.findAll()).thenReturn(emptyList());
        assertTrue(sut.findAll().isEmpty());
    }
}
