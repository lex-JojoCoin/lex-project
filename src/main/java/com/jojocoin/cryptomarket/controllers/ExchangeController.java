package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.dtos.request.PurchaseOrderRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleOrderRequestDto;
import com.jojocoin.cryptomarket.dtos.request.OperationRequest;
import com.jojocoin.cryptomarket.dtos.response.BookMarketResponseDto;
import com.jojocoin.cryptomarket.dtos.response.OperationResponse;
import com.jojocoin.cryptomarket.models.BookMarketModel;
import com.jojocoin.cryptomarket.models.ExchangeModel;
import com.jojocoin.cryptomarket.services.interfaces.ExchangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ExchangeModel> findExchange(){
        ExchangeModel exchange = service.findExchange();
        log.info("Find the exchange");
        return new ResponseEntity<>(exchange, HttpStatus.OK);
    }
    @PostMapping("/orders/create/sales")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<BookMarketResponseDto> createSaleOrder(@RequestBody @Valid SaleOrderRequestDto request){
        BookMarketModel book = service.createSaleOrder(request);
        log.info("Created a new sale order on database");
        return new ResponseEntity<>(new BookMarketResponseDto(book), HttpStatus.CREATED);
    }

    @PostMapping("/orders/create/purchases")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<BookMarketResponseDto> createPurchaseOrder(@RequestBody @Valid PurchaseOrderRequestDto request){
        BookMarketModel book = service.createPurchaseOrder(request);
        log.info("Created a new purchase order on database");
        return new ResponseEntity<>(new BookMarketResponseDto(book), HttpStatus.CREATED);
    }

    @PostMapping("/orders/buy")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<OperationResponse> buyCoin(@RequestBody @Valid OperationRequest request){
        OperationResponse response = service.purchaseCoins(request);
        log.info("New operation done! A client managed to successfully buy a coin!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/orders/sell")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<OperationResponse> sellCoin(@RequestBody @Valid OperationRequest request){
        OperationResponse response = service.sellCoins(request);
        log.info("New operation done! A client managed to successfully sell a coin!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
