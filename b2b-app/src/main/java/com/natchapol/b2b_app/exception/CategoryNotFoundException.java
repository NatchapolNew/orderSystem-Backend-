package com.natchapol.b2b_app.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
      super("Category id "+ id +" not found");
    }
}
