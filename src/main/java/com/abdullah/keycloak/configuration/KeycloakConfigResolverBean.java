package com.abdullah.keycloak.configuration;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Syed Abdullah
 * Date: 19-Jan-2022
 * Time: 2:26 PM
 */
@Configuration
public class KeycloakConfigResolverBean {
    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
