package com.jojocoin.cryptomarket.exceptions;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {
        super("Conflict! Insufficient balance to make the operation! Assert that you have the sufficient balance");
    }
}
