package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.dtos.request.CryptoWalletRequestDto;
import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.services.CryptoWalletServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@AllArgsConstructor
@RequestMapping("/cryptoWallets")
public class CryptoWalletController {
    private final CryptoWalletServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<CryptoWalletModel> findByID(@PathVariable Long id){
        CryptoWalletModel cryptoWallet = service.findById(id);
        return new ResponseEntity<>(cryptoWallet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CryptoWalletModel> save(@RequestBody CryptoWalletRequestDto request){
        CryptoWalletModel cryptoWallet = service.save(request);
        return new ResponseEntity<>(cryptoWallet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CryptoWalletModel> update(@PathVariable Long id,
                                                    @RequestBody CryptoWalletRequestDto request){
        CryptoWalletModel cryptoWallet = service.update(id, request);
        return new ResponseEntity<>(cryptoWallet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}