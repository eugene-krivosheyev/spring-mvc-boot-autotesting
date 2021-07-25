package com.acme.dbo.config;

import com.acme.dbo.dao.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
@Import(Config.class)
public class TestConfig {
    private static Logger log = LoggerFactory.getLogger(TestConfig.class);

    @Bean @Primary
    public AccountRepository accountRepositoryStub(@Value("${accounts.repo.init-capacity}") int initCapacity) {
        log.debug("Creating accountRepositoryStub with initial capacity {}", initCapacity);
        return mock(AccountRepository.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
