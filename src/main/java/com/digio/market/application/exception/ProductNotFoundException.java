package com.digio.market.application.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(final String msg) {
        super(msg);
    }
}
