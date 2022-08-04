package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.BookMarketModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BookMarketRepository extends JpaRepository<BookMarketModel, UUID> {
}
