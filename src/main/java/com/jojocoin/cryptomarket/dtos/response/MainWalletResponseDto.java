package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class MainWalletResponseDto {

    private BigDecimal balance;
    private List<CryptoWalletModel> cryptoWalletModels;

    public MainWalletResponseDto(MainWalletModel model){
        this.balance = model.getBalance();
        this.cryptoWalletModels = model.getCryptoWalletModels();
    }
}
