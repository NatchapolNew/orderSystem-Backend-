package com.natchapol.b2b_app.services.impl;

import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.entity.ProductsEntity;
import com.natchapol.b2b_app.exception.AlreadyExistProduct;
import com.natchapol.b2b_app.mapper.ProductMapper;
import com.natchapol.b2b_app.repository.ProductsRepository;
import com.natchapol.b2b_app.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public ProductResponseDTO createProduct(CreateProductDTO product) {
        Optional<ProductsEntity> CheckProduct = productsRepository.findByName(product.getName());
        if (CheckProduct.isPresent()){
            throw new AlreadyExistProduct("มีสินค้านี้แล้วในระบบ");
        }else{
        ProductsEntity productsEntity = ProductMapper.INSTANCE.toEntity(product);
        ProductsEntity products = productsRepository.save(productsEntity);
        ProductResponseDTO productResponseDTO = ProductMapper.INSTANCE.toResponseDTO(products);
        return productResponseDTO;
        }
    }
}
