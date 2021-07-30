package com.example.investrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;

import com.example.investrest.services.StockService;

@SpringBootApplication
@EnableCaching
public class InvestRestApplication {

	private static final Logger log = LoggerFactory.getLogger(InvestRestApplication.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(InvestRestApplication.class);
		application.run(args);
	}

	@PostConstruct
	public void init(){
		log.info("");
		log.info("========================= Invest REST Application startup =========================");
		log.info("");

		StockService stockService = new StockService();
		stockService.registerClearStocksCache();
	};

}
