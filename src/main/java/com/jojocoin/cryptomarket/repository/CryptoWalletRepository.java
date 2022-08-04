package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoWalletRepository extends JpaRepository<CryptoWalletModel, Long> {

}
