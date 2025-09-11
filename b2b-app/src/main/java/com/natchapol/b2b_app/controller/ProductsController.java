package com.natchapol.b2b_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@Valid @RequestPart("product")String productString, @RequestPart("file")MultipartFile file){
       ObjectMapper objectMapper = new ObjectMapper();
       CreateProductDTO createProductDTO = null;
       try {
           createProductDTO = objectMapper.readValue(productString, CreateProductDTO.class);
       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       }
             ProductResponseDTO product = productService.createProduct(createProductDTO,file);
        return product;
    };

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean deleteProduct(@PathVariable Long id){
        boolean deleteProduct = productService.deleteProduct(id);
        if (deleteProduct == true){
            return true;
        }else {
            return false;
        }

    }

}
