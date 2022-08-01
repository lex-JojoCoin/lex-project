package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MainWalletRequestDto {

    @NotNull
    private BigDecimal balance;
}
