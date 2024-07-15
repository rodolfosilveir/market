package com.digio.market.application.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(final String msg) {
        super(msg);
    }
}
