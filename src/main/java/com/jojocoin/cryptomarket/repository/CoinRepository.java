package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.CoinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<CoinModel, String> {
}
