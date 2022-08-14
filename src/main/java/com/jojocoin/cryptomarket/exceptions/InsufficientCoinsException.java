package com.jojocoin.cryptomarket.exceptions;

public class InsufficientCoinsException extends RuntimeException {
    public InsufficientCoinsException() {
        super("Conflict! Insufficient coins to make the operation! Assert that you have the informed quantity ");
    }
}
