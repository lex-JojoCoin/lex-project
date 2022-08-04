package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CryptoWalletResponse {

    private BigDecimal amount;
    private BigDecimal balance;
    private CoinModel coin;

    public CryptoWalletResponse(CryptoWalletModel model) {
        this.amount = model.getAmount();
        this.balance = model.getBalance();
        this.coin = model.getCoin();
    }
}
