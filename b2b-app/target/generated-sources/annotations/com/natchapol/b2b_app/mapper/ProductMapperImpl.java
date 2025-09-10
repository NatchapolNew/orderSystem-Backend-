package com.natchapol.b2b_app.mapper;

import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.entity.ProductsEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-10T14:23:39+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDTO toResponseDTO(ProductsEntity products) {
        if ( products == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId( products.getId() );
        productResponseDTO.setName( products.getName() );
        productResponseDTO.setDescription( products.getDescription() );
        productResponseDTO.setPrice( products.getPrice() );
        productResponseDTO.setStock( products.getStock() );

        return productResponseDTO;
    }

    @Override
    public ProductsEntity toEntity(CreateProductDTO createProductDTO) {
        if ( createProductDTO == null ) {
            return null;
        }

        ProductsEntity.ProductsEntityBuilder productsEntity = ProductsEntity.builder();

        productsEntity.name( createProductDTO.getName() );
        productsEntity.description( createProductDTO.getDescription() );
        productsEntity.price( createProductDTO.getPrice() );
        productsEntity.stock( createProductDTO.getStock() );

        return productsEntity.build();
    }
}
