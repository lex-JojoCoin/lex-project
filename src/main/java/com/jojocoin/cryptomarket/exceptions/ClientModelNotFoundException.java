package com.jojocoin.cryptomarket.exceptions;

public class ClientModelNotFoundException extends RuntimeException {

    public ClientModelNotFoundException(String msg) {
        super(msg);
    }

    public ClientModelNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
