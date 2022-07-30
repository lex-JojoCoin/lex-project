package com.jojocoin.cryptomarket.dtos.request;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWalletRequestDto {

    //@NotEmpty
    //private CoinModel coin;
    @NotEmpty
    private BigDecimal saldo;
    @NotEmpty
    private BigDecimal amount;
    @NotEmpty
    private Long privateKey;
    @NotEmpty
    private Long publicKey;
}
