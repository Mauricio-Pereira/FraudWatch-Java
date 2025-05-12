package com.fiap.FraudWatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FraudWatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudWatchApplication.class, args);
	}
//	spring.datasource.username=${dbUser}
//spring.datasource.password=${dbPassword}
}
