package com.jojocoin.cryptomarket.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "TB_CLIENT")
public class ClientModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID clientId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String pix;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "mainWallet_id")
    private WalletModel mainWallet;
    @OneToMany(mappedBy = "client")
    private List<WalletModel> wallets;
    @OneToMany(mappedBy = "client")
    private List<CardModel> cards;
}
