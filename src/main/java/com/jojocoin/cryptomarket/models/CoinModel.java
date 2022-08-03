package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_COINS")
public class CoinModel implements Serializable {

    @Id
    private String id;
    @Column(nullable = false, unique = true)
    private String symbol;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private BigDecimal priceUsd;

}
