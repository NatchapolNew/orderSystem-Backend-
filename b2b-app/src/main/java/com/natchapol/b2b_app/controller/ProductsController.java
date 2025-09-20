package com.natchapol.b2b_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.request.UpdateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@Valid @RequestPart("product")String productString, @RequestPart("file")MultipartFile file){
        //ทำการmap object ที่มาจาก frontend แปลงจากjson -> javaobject
       ObjectMapper objectMapper = new ObjectMapper();
       CreateProductDTO createProductDTO = null;
       try {
           //map string content กับ type class
           createProductDTO = objectMapper.readValue(productString, CreateProductDTO.class);
       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       }
             ProductResponseDTO product = productService.createProduct(createProductDTO,file);
        return product;
    };

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteProduct(@PathVariable Long id){
        boolean deleteProduct = productService.deleteProduct(id);
        if (deleteProduct == true){
            return true;
        }else {
            return false;
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO updatedProduct(@Valid @PathVariable Long id,@RequestPart("product")String productString,@RequestPart("file")MultipartFile file){
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateProductDTO updateProductDTO = null;
        try {
            updateProductDTO = objectMapper.readValue(productString, UpdateProductDTO.class);
        }catch (JsonProcessingException e){
            throw  new RuntimeException(e);
        }

        ProductResponseDTO productResponseDTO = productService.updateProduct(id,updateProductDTO,file);



        return productResponseDTO;
    }

    @GetMapping
    public List<ProductResponseDTO> readAllProducts(){
         List<ProductResponseDTO> productResponseDTO = productService.readAllProduct();
        return productResponseDTO;
    }

    @GetMapping("/search")
    public List<ProductResponseDTO> readProductByName(@RequestParam(value = "name",required = false) String name){
        List<ProductResponseDTO> allProduct = productService.readProductByName(name);
        return allProduct;
    }



}
