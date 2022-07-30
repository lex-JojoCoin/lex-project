package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.ClientModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class ClientResponseDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String username;

    public ClientResponseDto(ClientModel model) {
        this.name = model.getName();
        this.cpf = model.getCpf();
        this.username = model.getUser().getUsername();
    }
}
