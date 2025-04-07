package com.example.HR.Authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HrAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrAuthenticationApplication.class, args);
	}

}
