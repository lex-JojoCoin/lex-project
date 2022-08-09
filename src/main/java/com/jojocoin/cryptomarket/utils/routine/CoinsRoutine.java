package com.jojocoin.cryptomarket.utils.routine;

import com.jojocoin.cryptomarket.feign.CoinClientFeignService;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import com.jojocoin.cryptomarket.services.interfaces.CryptoWalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CoinsRoutine {

    private final CoinClientFeignService feignService;
    private final CoinService service;

    private final CryptoWalletService walletService;

    @Scheduled(fixedDelay = 300000)
    public void getCoinPricesUpdated(){
        log.info("Starting routine...");
        service.saveAll();
        log.info("Saved all coins values updated");
        walletService.updateBalance();
        log.info("Updated all crypto wallets values updated");
        log.info("Ending routine...");
    }
}
