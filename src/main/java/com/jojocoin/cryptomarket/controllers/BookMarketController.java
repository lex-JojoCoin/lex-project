package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.dtos.request.BookMarketRequestDto;
import com.jojocoin.cryptomarket.dtos.request.OrderRequestDto;
import com.jojocoin.cryptomarket.dtos.response.BookMarketResponseDto;
import com.jojocoin.cryptomarket.dtos.response.OrderResponseDto;
import com.jojocoin.cryptomarket.models.BookMarketModel;
import com.jojocoin.cryptomarket.models.OrderModel;
import com.jojocoin.cryptomarket.services.interfaces.BookMarketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookMarketController {

    private final BookMarketService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<BookMarketModel>> findAll() {
        List<BookMarketModel> all = service.findAll();
        log.info("All books found");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/findById/{Id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<BookMarketModel> findbyId(@PathVariable UUID id) {
        BookMarketModel order = service.findById(id);
        log.info("Order found by Id: "+ id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookMarketResponseDto> save(@RequestBody @Valid BookMarketRequestDto request){
        BookMarketModel save = service.save(request);
        log.info("New book saved on database");
        return new ResponseEntity<>(new BookMarketResponseDto(save), HttpStatus.CREATED);
    }

    @PutMapping("/updateById/{Id}")
    public ResponseEntity<BookMarketResponseDto> update(@PathVariable UUID id,
                                                   @RequestBody @Valid BookMarketRequestDto request){
        BookMarketModel update = service.update(id, request);
        log.info("Book updated on database by id: " + id);
        return new ResponseEntity<>(new BookMarketResponseDto(update), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        service.deleteById(id);
        log.info("Book deleted from database by id");
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
