package com.natchapol.b2b_app.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Product id"+ id +"not found");
    }
}
