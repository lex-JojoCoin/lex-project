package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWalletRequestDto {


    @NotNull
    @Min(value = 1)
    private BigDecimal amount;
    @NotNull
    private BigDecimal price;
    @NotEmpty
    private String coinName;

}