package com.jojocoin.cryptomarket.exceptions;

import java.math.BigDecimal;

public class IllegalOrderOperation extends RuntimeException {

    public IllegalOrderOperation() {
        super("Illegal order operation! The order is closed!");
    }

    public IllegalOrderOperation(BigDecimal value) {
        super("Illegal order operation! The balance is not enough to perform the operation!");
    }

    public IllegalOrderOperation(String coin){
        super("Illegal order operation! The client don't have a crypto wallet with the coin: " + coin);
    }
}
