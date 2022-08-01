package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWalletRequestDto {

    @NotEmpty
    private BigDecimal amount;
    @NotEmpty
    private BigDecimal saldo;
    @NotEmpty
    private Long publicKey;

}
