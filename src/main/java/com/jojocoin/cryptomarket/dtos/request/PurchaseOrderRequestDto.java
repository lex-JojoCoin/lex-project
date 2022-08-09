package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PurchaseOrderRequestDto {

    @CPF
    @NotEmpty
    private String cpf;
    @NotNull
    private Integer amount;
    @NotNull
    @Min(value = 1)
    private BigDecimal price;
    @NotEmpty
    private String coin;
}
