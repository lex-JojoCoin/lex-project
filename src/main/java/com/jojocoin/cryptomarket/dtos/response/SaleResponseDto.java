package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.SaleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaleResponseDto {
    @NotNull
    private CoinModel coin;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal sellingPrice;

    public SaleResponseDto(SaleModel sale) {
        this.coin = sale.getCoin();
        this.quantity = sale.getQuantity();
        this.sellingPrice = sale.getSellingPrice();
    }
}
