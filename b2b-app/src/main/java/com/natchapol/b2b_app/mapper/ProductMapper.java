package com.natchapol.b2b_app.mapper;

import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.request.UpdateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.entity.ProductsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper{
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductResponseDTO toResponseDTO(ProductsEntity products);
    ProductsEntity toEntity(CreateProductDTO createProductDTO);
    ProductsEntity toEntity(UpdateProductDTO updateProductDTO);
}
