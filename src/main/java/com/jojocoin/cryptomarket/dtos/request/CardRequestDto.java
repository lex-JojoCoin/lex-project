package com.jojocoin.cryptomarket.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {

    @NotEmpty
    private String network;
    @NotEmpty
    @Size(max = 16)
    @CreditCardNumber
    private String number;
    @NotEmpty
    private String name;
    @NotEmpty
    @Size(max = 3)
    private String cvv;
    @NotNull
    @JsonFormat(pattern = "MM/yyyy")
    private String expiration;
}
