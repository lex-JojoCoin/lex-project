package com.jojocoin.cryptomarket.dtos;

import com.jojocoin.cryptomarket.models.UserModel;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ClientDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String pix;
    @NotEmpty
    private UserModel user;
    @NotEmpty
    private WalletModel mainWallet;
    @NotEmpty
    private List<WalletModel> wallets;
    @NotEmpty
    private List<CardModel> cards;
}
