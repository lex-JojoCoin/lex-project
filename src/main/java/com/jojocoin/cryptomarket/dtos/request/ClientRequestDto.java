package com.jojocoin.cryptomarket.dtos.request;

import com.jojocoin.cryptomarket.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

}
