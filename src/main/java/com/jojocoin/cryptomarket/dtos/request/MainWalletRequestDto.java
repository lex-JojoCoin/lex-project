package com.jojocoin.cryptomarket.dtos.request;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MainWalletRequestDto {

    @NotEmpty
    private List<CryptoWalletModel> cryptoWallets;
    @NotNull
    private BigDecimal balance;

    //Todo: delete this constructor
    public MainWalletRequestDto(BigDecimal balance){
        this.balance = balance;
    }
}
