package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;
<<<<<<< HEAD
import com.jojocoin.cryptomarket.models.MainWalletModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoWalletRepository extends JpaRepository<CryptoWalletModel, Long> {

=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoWalletRepository extends JpaRepository<CryptoWalletModel, Long> {
>>>>>>> main
}
