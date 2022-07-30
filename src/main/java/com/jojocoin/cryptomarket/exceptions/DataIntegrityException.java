package com.jojocoin.cryptomarket.exceptions;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataIntegrityException() {
        super("It's not possible to delete this object, it would violate other data integrity.");
    }
}
