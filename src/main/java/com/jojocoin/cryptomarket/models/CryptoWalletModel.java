package com.jojocoin.cryptomarket.models;

import com.jojocoin.cryptomarket.enums.CoinName;
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
    /*
    @OneToOne
    @JoinColumn(name = "", nullable = false)
    private CoinName typeCoin;
    */
    private BigDecimal amount;
    private BigDecimal saldo;
    @Column(nullable = false, unique = true)
    private Long publicKey;
    @Column(nullable = false, unique = true)
    private Long privateKey;

}