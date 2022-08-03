package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {

    @NotEmpty
    private String name;
    @CPF
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
