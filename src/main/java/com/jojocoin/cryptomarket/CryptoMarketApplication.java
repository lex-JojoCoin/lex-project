package com.jojocoin.cryptomarket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class CryptoMarketApplication {

	public static void main(String[] args) {
		log.info("Starting api jojocoin crypto market");
		SpringApplication.run(CryptoMarketApplication.class, args);
		log.info("Api started and ready to get requests ");
	}
}
