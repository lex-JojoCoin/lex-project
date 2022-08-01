package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.MainWalletRequestDto;
import com.jojocoin.cryptomarket.models.MainWalletModel;

import java.util.List;

public interface MainWalletService {

    List<MainWalletModel> findAll();
    MainWalletModel findById(Long id);
    MainWalletModel save(MainWalletRequestDto request);
    MainWalletModel update(Long id, MainWalletRequestDto request);
    void deleteById(Long id);
}
