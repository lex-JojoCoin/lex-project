package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_CRYPTO_WALLET")
public class CryptoWalletModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private BigDecimal balance;
    @OneToOne
    private CoinModel coin;

    public void updateBalance(){
        this.balance = coin.getPriceUsd().multiply(amount);
    }

    public void addAmount(BigDecimal value){
        this.amount = amount.add(value);
        this.balance = balance.add(coin.getPriceUsd().multiply(amount));
    }

    public void subAmount(BigDecimal value){
        this.amount = amount.subtract(value);
        this.balance = balance.subtract(coin.getPriceUsd().multiply(amount));
    }
}
