package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainWalletRepository extends JpaRepository<MainWalletModel, Long> {
    List<CryptoWalletModel> listCryptoWallets(Long id);

}
