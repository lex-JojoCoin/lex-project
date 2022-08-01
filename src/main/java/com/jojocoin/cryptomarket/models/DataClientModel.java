package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataClientModel implements Serializable {

    private List<CoinModel> data;
    private Long timestamp;
}
