package com.jojocoin.cryptomarket.exceptions;

public class InvalidCardException extends RuntimeException {

    public InvalidCardException() {
        super("Conflict! Invalid card! Please, verify if this card is your card and the data given.");
    }
}
