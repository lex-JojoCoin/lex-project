package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.models.CoinModel;

import java.util.List;

public interface CoinService {

    List<CoinModel> findAll();
    CoinModel findById(String name);
}
