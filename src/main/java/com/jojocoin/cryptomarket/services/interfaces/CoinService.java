package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.models.CoinModel;

import java.util.List;

public interface CoinService {

    void saveAll();
    List<CoinModel> findAll();
    CoinModel findById(String name);
}
