package com.jojocoin.cryptomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class CryptoMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoMarketApplication.class, args);
	}
}
