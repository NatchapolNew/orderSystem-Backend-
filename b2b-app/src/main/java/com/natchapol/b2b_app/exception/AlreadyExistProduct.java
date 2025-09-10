package com.natchapol.b2b_app.exception;

public class AlreadyExistProduct extends RuntimeException {
    public AlreadyExistProduct(String message) {
        super(message);
    }
}
