package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.MainWalletRequestDto;
import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.models.MainWalletModel;

import java.util.List;

public interface MainWalletService {

    List<MainWalletModel> findAll();
    List<CryptoWalletModel> listCryptoWallets(Long id);
    //O id a ser passado é o da main wallet, aí puxa as cryptoWallets associadas com o id da main wallet
    MainWalletModel findById(Long id);
    MainWalletModel save(MainWalletRequestDto request);
    MainWalletModel update(Long id, MainWalletRequestDto request);
    void deleteById(Long id);
}
