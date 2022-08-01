package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_MAIN_WALLET")
public class MainWalletModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal balance;
    @OneToMany
    @JoinColumn(name = "cryptoWallet_id")
    private List<CryptoWalletModel> cryptoWalletModels;

    //@OneToOne
    //@JoinColumn(name = "transaction_id")
    //private List<Transaction> transactionLog;
}
