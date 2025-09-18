package com.natchapol.b2b_app.mapper;

import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.request.UpdateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.entity.ProductsEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-18T09:50:12+0700",
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
        productResponseDTO.setImgUrl( products.getImgUrl() );

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
        productsEntity.imgUrl( createProductDTO.getImgUrl() );

        return productsEntity.build();
    }

    @Override
    public ProductsEntity toEntity(UpdateProductDTO updateProductDTO) {
        if ( updateProductDTO == null ) {
            return null;
        }

        ProductsEntity.ProductsEntityBuilder productsEntity = ProductsEntity.builder();

        productsEntity.name( updateProductDTO.getName() );
        productsEntity.description( updateProductDTO.getDescription() );
        productsEntity.price( updateProductDTO.getPrice() );
        productsEntity.stock( updateProductDTO.getStock() );
        productsEntity.imgUrl( updateProductDTO.getImgUrl() );

        return productsEntity.build();
    }

    @Override
    public void updateEntityFromDto(UpdateProductDTO updateProductDTO, ProductsEntity productsEntity) {
        if ( updateProductDTO == null ) {
            return;
        }

        productsEntity.setName( updateProductDTO.getName() );
        productsEntity.setDescription( updateProductDTO.getDescription() );
        productsEntity.setPrice( updateProductDTO.getPrice() );
        productsEntity.setStock( updateProductDTO.getStock() );
        productsEntity.setImgUrl( updateProductDTO.getImgUrl() );
    }
}
