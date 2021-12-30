package com.abdullah.keycloak.configuration;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        // When securing a service-to-service application, instead, we would use a NullAuthenticatedSessionStrategy.
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        // Note: Like ROLE_Member and ROLE_Librarian. For Member & Librarian role
        grantedAuthorityMapper.setPrefix("ROLE_");

        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }
    /*
    Handle Bean Definition Overriding Issues
    Since Spring 2.1.0, the spring.main.allow-bean-definition-overriding property is set to false by default, differently from the previous versions of Spring. It means that it's not allowed anymore to override a bean already defined.
    The SecurityConfig class we are working on extends KeycloakWebSecurityConfigurerAdapter, which defines an HttpSessionManager bean. But this bean has already been defined somewhere else in the Keycloak Adapter library. Therefore, it triggers an error in Spring 2.1.0+.

    We could fix the problem by changing the value for spring.main.allow-bean-definition-overriding to true. I prefer adjusting the bean definition to be loaded conditionally only if no other bean of that type has been defined.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/books").hasAnyRole("Member", "Librarian")
                .antMatchers("/manager").hasRole("Librarian")
                .anyRequest().permitAll();
    }
}
