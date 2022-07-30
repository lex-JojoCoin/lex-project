package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_Coin")
public class CoinModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
