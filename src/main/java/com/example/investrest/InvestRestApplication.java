package com.example.investrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InvestRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestRestApplication.class, args);
	}

}
