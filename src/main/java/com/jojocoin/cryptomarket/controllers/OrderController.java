package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.dtos.request.OrderRequestDto;
import com.jojocoin.cryptomarket.dtos.response.OrderResponseDto;
import com.jojocoin.cryptomarket.models.OrderModel;
import com.jojocoin.cryptomarket.services.interfaces.OrderService;
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
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<OrderModel>> findAll() {
        List<OrderModel> all = service.findAll();
        log.info("All orders found");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/findById/{Id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<OrderModel> findbyId(@PathVariable UUID id) {
        OrderModel order = service.findById(id);
        log.info("Order found by Id: "+ id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> save(@RequestBody @Valid OrderRequestDto request){
        OrderModel save = service.save(request);
        log.info("New order saved on database");
        return new ResponseEntity<>(new OrderResponseDto(save), HttpStatus.CREATED);
    }

    @PutMapping("/updateById/{Id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable UUID id,
                                              @RequestBody @Valid OrderRequestDto request){
        OrderModel update = service.update(id, request);
        log.info("Order updated on database by id: " + id);
        return new ResponseEntity<>(new OrderResponseDto(update), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        service.deleteById(id);
        log.info("Order deleted from database by id");
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
