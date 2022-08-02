package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.dtos.request.CardRequestDto;
import com.jojocoin.cryptomarket.models.ClientModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class ClientResponseDto {

    private String name;
    private String cpf;
    private String pix;
    private String username;
    private MainWalletResponseDto mainWallet;
    private List<CardResponseDto> cards;

    public ClientResponseDto(ClientModel model) {
        this.name = model.getName();
        this.cpf = model.getCpf();
        this.pix = model.getPix();
        this.username = model.getUser().getUsername();
        this.mainWallet = new MainWalletResponseDto(model.getMainWallet());
        this.cards = model.getCards().stream().map(CardResponseDto::new).toList();
    }
}
