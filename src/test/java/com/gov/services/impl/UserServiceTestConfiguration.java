package com.gov.services.impl;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class UserServiceTestConfiguration {

    @Bean
    @Primary
    public UserManagerImpl nameService() {
        return Mockito.mock(UserManagerImpl.class);
    }
}
