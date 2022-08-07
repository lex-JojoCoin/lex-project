package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sellerCpf;
    private CoinModel coin;
    private Integer quantity;
    private BigDecimal sellingPrice;
}
