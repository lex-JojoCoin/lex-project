package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    @Column(unique = true)
    private String pix;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @OneToOne (mappedBy = "client", cascade = CascadeType.ALL)
    @JoinColumn(name = "main_wallet_id", nullable = false)
    private MainWalletModel mainWallet;

    //TODO: Ver as relações e as entidades comentadas
//    @OneToMany
//    private List<CardModel> cards;
}
