package com.abdullah.keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class SpringBootKeyCloakDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKeyCloakDemoApplication.class, args);
	}

}
