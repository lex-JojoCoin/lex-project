package com.jojocoin.cryptomarket.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.YearMonth;

@Getter
@NoArgsConstructor
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

    public CardRequestDto(String network, String number, String name, String cvv, String expiration) {
        this.network = network;
        this.number = number;
        this.name = name;
        this.cvv = cvv;
        this.expiration = expiration;
    }
}
