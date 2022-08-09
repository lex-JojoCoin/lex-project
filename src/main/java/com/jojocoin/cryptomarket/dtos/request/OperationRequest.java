package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class OperationRequest {

    @NotNull
    private Long orderId;
    @CPF
    @NotEmpty
    private String clientCpf;
}
