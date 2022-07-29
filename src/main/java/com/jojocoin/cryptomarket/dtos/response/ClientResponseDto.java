package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String username;
}
