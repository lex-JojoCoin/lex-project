package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_MAIN_WALLET")
public class MainWallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private BigDecimal saldo;
    @OneToMany
    @JoinColumn(name = "cryptoWallet_id", nullable = false)
    private List<CryptoWallet> cryptoWallets;
    //@OneToOne
    //@JoinColumn(name = "transaction_id")
    //private List<Transaction> transactionLog;
}
