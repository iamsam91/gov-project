package com.gov.services.impl;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.gov.services.UserManager;

@Profile("test")
@Configuration
public class UserServiceTestConfiguration {

    @Bean
    @Primary
    public UserManager nameService() {
        return Mockito.mock(UserManager.class);
    }
    
}
