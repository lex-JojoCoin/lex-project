package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.CardRequestDto;
import com.jojocoin.cryptomarket.dtos.request.ClientRequestDto;
import com.jojocoin.cryptomarket.dtos.request.CryptoWalletRequestDto;
import com.jojocoin.cryptomarket.models.ClientModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<ClientModel> findAll();
    ClientModel findById(UUID id);
    ClientModel findByCpf(String cpf);
    ClientModel save(ClientRequestDto request);
    ClientModel update(UUID id, ClientRequestDto request);
    ClientModel update(String cpf, ClientRequestDto request);
    ClientModel update(ClientModel entity);
    void delete(UUID id);
    void delete(String cpf);
    ClientModel addCreditCard(String cpf, CardRequestDto request);
    ClientModel createCryptoWallet(String cpf, CryptoWalletRequestDto request);
    ClientModel addBalanceOnMainWalletByCard(String cpf, CardRequestDto request, BigDecimal value);
    ClientModel addBalanceOnMainWalletByPix(String cpf, String pix, BigDecimal value);
    void updateAddCryptoWallet(String cpf, String coin, CryptoWalletRequestDto request);
    void updateSubCryptoWallet(String cpf, String coin, CryptoWalletRequestDto request);

}
