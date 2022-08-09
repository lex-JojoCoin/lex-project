package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import lombok.Getter;


import java.math.BigDecimal;
import java.util.List;

@Getter
public class MainWalletResponseDto {

    private final BigDecimal balance;
    private final List<CryptoWalletModel> cryptoWalletModels;

    public MainWalletResponseDto(MainWalletModel model){
        this.balance = model.getBalance();
        this.cryptoWalletModels = model.getCryptoWalletModels();
    }

}
