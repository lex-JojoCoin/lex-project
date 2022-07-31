package com.jojocoin.cryptomarket.controllers;


import com.jojocoin.cryptomarket.dtos.request.MainWalletRequestDto;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import com.jojocoin.cryptomarket.services.interfaces.MainWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wallets")
public class WalletController{

    // Injeção de dependência
    @Autowired
    private MainWalletService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<MainWalletModel>> findAll(){
        List<MainWalletModel> models = service.findAll();
        log.info("All wallets founded");
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainWalletModel> findById(@PathVariable Long id){
        MainWalletModel model = service.findById(id);
        log.info("User wallet from the given id");
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MainWalletModel> save(@RequestBody MainWalletRequestDto request){
        MainWalletModel model = service.save(request);
        log.info("New wallet created");
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainWalletModel> update(@PathVariable Long id,
                                            MainWalletRequestDto request){
        MainWalletModel model = service.update(id, request);
        log.info("Wallet updated");
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        service.deleteById(id);
        log.info("Deleted wallet");
        return new ResponseEntity<>("Ok!", HttpStatus.OK);
    }

}
