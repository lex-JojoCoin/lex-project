package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CryptoWalletResponse {

    private final BigDecimal amount;
    private final BigDecimal balance;
    private final CoinModel coin;

    public CryptoWalletResponse(CryptoWalletModel model) {
        this.amount = model.getAmount();
        this.balance = model.getBalance();
        this.coin = model.getCoin();
    }
}
