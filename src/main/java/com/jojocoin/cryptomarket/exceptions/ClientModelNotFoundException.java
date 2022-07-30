package com.jojocoin.cryptomarket.exceptions;

import java.util.UUID;

public class ClientModelNotFoundException extends RuntimeException {

    public ClientModelNotFoundException(String msg) {
        super(msg);
    }

    public ClientModelNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ClientModelNotFoundException(UUID id) {
        super("Client not found for the given id: " + id);
    }
}
