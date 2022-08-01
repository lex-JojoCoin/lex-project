package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.ClientModel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ClientResponseDto {

    private String name;
    private String cpf;
    private String pix;
    private String username;
    private MainWalletResponseDto mainWallet;

    public ClientResponseDto(ClientModel model) {
        this.name = model.getName();
        this.cpf = model.getCpf();
        this.pix = model.getPix();
        this.username = model.getUser().getUsername();
        this.mainWallet = new MainWalletResponseDto(model.getMainWallet());
    }
}
