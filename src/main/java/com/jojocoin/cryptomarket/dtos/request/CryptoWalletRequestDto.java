package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWalletRequestDto {

    @NotEmpty
    private BigDecimal amount;
    @NotEmpty
    private BigDecimal balance;
    @NotEmpty
    private String coinName;
}