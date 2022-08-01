package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.repository.CoinRepository;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    public List<CoinModel> findAll(){
        return coinRepository.findAll();
    }

    public CoinModel findById(String name){
        return coinRepository.findById(name).orElseThrow(()-> new ResourceNotFoundException(name));
    }
}
