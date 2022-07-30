package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_CRYPTO_WALLET")
public class CryptoWalletModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@ManyToOne
    //@JoinColumn(name = "Coin_id", nullable = false)
    //private CoinModel coin;
    private BigDecimal amount;
    private BigDecimal saldo;
    private Long privateKey;
    private Long publicKey;

}
