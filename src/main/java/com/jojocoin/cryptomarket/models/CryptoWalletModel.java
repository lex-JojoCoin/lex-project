package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_CRYPTO_WALLET")
public class CryptoWalletModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
