package com.jojocoin.cryptomarket.exceptions;

public class IllegalSaleOperation extends RuntimeException{

    public IllegalSaleOperation() {
        super("Illegal order operation! The order is not a sale!");
    }
}
