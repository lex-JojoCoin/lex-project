package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.MainWalletModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainWalletRepository extends JpaRepository<MainWalletModel, Long> {

}
