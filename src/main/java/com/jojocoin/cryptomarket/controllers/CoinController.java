package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.feign.CoinClientFeignService;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.DataClientModel;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/coins")
public class CoinController {

    private final CoinService service;

    @GetMapping
    public List<CoinModel> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public CoinModel findById(@PathVariable String id){
        return service.findById(id);
    }
}
