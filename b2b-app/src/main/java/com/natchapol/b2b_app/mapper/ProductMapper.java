package com.natchapol.b2b_app.mapper;

import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.request.UpdateProductDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.entity.ProductsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper{
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    //แปลงentity -> response

    ProductResponseDTO toResponseDTO(ProductsEntity products);

    //แปลง dto->entiy
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "category",ignore = true)
    ProductsEntity toEntity(CreateProductDTO createProductDTO);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "category",ignore = true)
    ProductsEntity toEntity(UpdateProductDTO updateProductDTO);

    //สำหรับ update entity ที่มีอยู่แล้ว
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "category",ignore = true)
    void updateEntityFromDto(UpdateProductDTO updateProductDTO, @MappingTarget ProductsEntity productsEntity);
}
