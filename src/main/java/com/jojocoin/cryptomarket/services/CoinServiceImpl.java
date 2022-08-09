package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.feign.CoinClientFeignService;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.repository.CoinRepository;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoinServiceImpl implements CoinService {

    private final CoinClientFeignService feignService;
    private final CoinRepository coinRepository;

    public void saveAll(){
        List<CoinModel> data = feignService.getAll().getData();
        coinRepository.saveAll(data);
    }

    public List<CoinModel> findAll(){
        return coinRepository.findAll();
    }

    public CoinModel findById(String name){
        return coinRepository.findById(name).orElseThrow(()-> new ResourceNotFoundException(name));
    }
}
