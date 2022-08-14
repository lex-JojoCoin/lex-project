package com.jojocoin.cryptomarket.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException() {
        super("Conflict! Order not found on book market!");
    }
}
