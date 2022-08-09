package com.jojocoin.cryptomarket.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found!");
    }

    public ResourceNotFoundException(UUID uuid) {
        super("Resource not found for the given id: " + uuid);
    }

    public ResourceNotFoundException(Long id) {
        super("Resource not found for the given id: " + id);
    }

    public ResourceNotFoundException(String text) {
        super("Resource not found for the given: " + text);
    }
}
