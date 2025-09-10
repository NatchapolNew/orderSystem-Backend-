package com.natchapol.b2b_app.controller;

import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private final ProductService productService;

    @PostMapping

    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@Valid @RequestBody CreateProductDTO createProductDTO){
             ProductResponseDTO product = productService.createProduct(createProductDTO);
        return product;
    };

}
