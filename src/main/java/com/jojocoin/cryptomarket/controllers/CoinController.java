package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/coins")
public class CoinController {

    private final CoinService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CoinModel>> findAll(){
        log.info("Found all coins on database");
        List<CoinModel> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<CoinModel> findById(@PathVariable String id){
        log.info("Found " + id + " coins on database");
        CoinModel coinModel = service.findById(id);
        return new ResponseEntity<>(coinModel, HttpStatus.OK);
    }
}
