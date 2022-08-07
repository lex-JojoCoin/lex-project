package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.dtos.request.PurchaseRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleRequestDto;
import com.jojocoin.cryptomarket.dtos.response.PurchaseResponseDto;
import com.jojocoin.cryptomarket.dtos.response.SaleResponseDto;
import com.jojocoin.cryptomarket.models.ExchangeModel;
import com.jojocoin.cryptomarket.models.SaleModel;
import com.jojocoin.cryptomarket.services.interfaces.ExchangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService service;


    @PostMapping("/purchaseCoins")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<PurchaseResponseDto> save(@RequestBody @Valid PurchaseRequestDto request) {
        ExchangeModel purchase = service.purchaseCoins(request);
        log.info("New purchase saved on database");
        return null;
        //return new ResponseEntity<>(new PurchaseResponseDto(purchase), HttpStatus.CREATED);
    }

    @PostMapping("/sellCoins")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<SaleResponseDto> save(@RequestBody @Valid SaleRequestDto request) {
        SaleModel sale = service.sellCoins(request);
        log.info("New sale saved on database");
        return new ResponseEntity<>(new SaleResponseDto(sale), HttpStatus.CREATED);
    }
}
