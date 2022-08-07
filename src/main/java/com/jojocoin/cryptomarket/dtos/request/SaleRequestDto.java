package com.jojocoin.cryptomarket.dtos.request;

import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.models.CoinModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaleRequestDto {
    @NotNull
    private String sellerCpf;
    @NotNull
    private CoinModel coin;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal sellingPrice;
}
