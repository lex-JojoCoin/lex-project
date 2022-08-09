package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.ClientModel;
import lombok.Getter;

import java.util.List;


@Getter
public class ClientResponseDto {

    private final String name;
    private final String cpf;
    private final String pix;
    private final String username;
    private final MainWalletResponseDto mainWallet;
    private final List<CardResponseDto> cards;

    public ClientResponseDto(ClientModel model) {
        this.name = model.getName();
        this.cpf = model.getCpf();
        this.pix = model.getPix();
        this.username = model.getUser().getUsername();
        this.mainWallet = new MainWalletResponseDto(model.getMainWallet());
        this.cards = model.getCards().stream().map(CardResponseDto::new).toList();
    }
}
