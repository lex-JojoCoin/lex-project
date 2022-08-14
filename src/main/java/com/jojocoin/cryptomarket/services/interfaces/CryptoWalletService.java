package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.CryptoWalletRequestDto;
import com.jojocoin.cryptomarket.models.CryptoWalletModel;

import java.util.List;

public interface CryptoWalletService {


    List<CryptoWalletModel> findAll();
    CryptoWalletModel findById(Long id);
    CryptoWalletModel save(CryptoWalletRequestDto request);
    List<CryptoWalletModel> saveAll(List<CryptoWalletModel> all);
    CryptoWalletModel update(Long id, CryptoWalletRequestDto request);
    CryptoWalletModel add(Long id, CryptoWalletRequestDto request);
    CryptoWalletModel subtract(Long id, CryptoWalletRequestDto request);

    void delete(Long id);
    void updateBalance();
}
