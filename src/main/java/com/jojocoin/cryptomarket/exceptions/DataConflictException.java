package com.jojocoin.cryptomarket.exceptions;

public class DataConflictException extends RuntimeException{

    public DataConflictException(String message) {
        super("CONFLICT!: " + message);
    }
}
