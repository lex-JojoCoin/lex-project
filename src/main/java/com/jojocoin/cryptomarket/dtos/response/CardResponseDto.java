package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.CardModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardResponseDto {

    private String network;
    private String number;

    public CardResponseDto(CardModel model) {
        this.network = model.getNetwork();
        this.number = encodeCarNumber(model.getNumber());
    }

    private String encodeCarNumber(String number) {
        String substring = number.substring(0, 11);
        String all = substring.replaceAll("\\d", "*");
        return all.concat(number.substring(12, 16));
    }
}
