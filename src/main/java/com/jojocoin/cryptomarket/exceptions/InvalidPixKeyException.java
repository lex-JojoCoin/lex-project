package com.jojocoin.cryptomarket.exceptions;

public class InvalidPixKeyException extends RuntimeException {

    public InvalidPixKeyException() {
        super("Conflict! Invalid pix key. Please, insert the pix key again.");
    }
}
