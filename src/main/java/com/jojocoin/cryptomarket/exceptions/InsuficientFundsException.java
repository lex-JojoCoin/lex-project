package com.jojocoin.cryptomarket.exceptions;

public class InsuficientFundsException extends RuntimeException {

    public InsuficientFundsException(String message) {
        super("Insuficient funds!: " + message);
    }
}
