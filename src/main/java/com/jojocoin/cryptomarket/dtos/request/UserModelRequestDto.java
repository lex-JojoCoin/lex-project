package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserModelRequestDto {

    private String username;
    private String password;
}
