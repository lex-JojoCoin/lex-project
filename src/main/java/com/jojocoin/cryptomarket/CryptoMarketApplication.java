package com.jojocoin.cryptomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CryptoMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoMarketApplication.class, args);
	}

	@GetMapping("/test")
	public String dummyTest(){
		return "Fuck you!";
	}

}
