package com.jojocoin.cryptomarket.exceptions;

public class CryptoWalletNotFoundException extends RuntimeException{

    public CryptoWalletNotFoundException(String coinName) {
        super("Conflict! Can't found the crypto wallet from the given coin: ");
    }
}
