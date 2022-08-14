package com.jojocoin.cryptomarket.exceptions;

public class IllegalPurchaseOperation extends RuntimeException{

    public IllegalPurchaseOperation() {
        super("Illegal order operation! The order is not a purchase!");
    }
}
