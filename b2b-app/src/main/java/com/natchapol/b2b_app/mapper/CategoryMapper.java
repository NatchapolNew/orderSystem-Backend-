package com.natchapol.b2b_app.mapper;

import com.natchapol.b2b_app.dto.request.CreateCategoryDTO;
import com.natchapol.b2b_app.dto.request.CreateProductDTO;
import com.natchapol.b2b_app.dto.request.UpdateCategoryDTO;
import com.natchapol.b2b_app.dto.response.CategoryResponseDTO;
import com.natchapol.b2b_app.dto.response.ProductResponseDTO;
import com.natchapol.b2b_app.entity.CategoryEntity;
import com.natchapol.b2b_app.entity.ProductsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //แปลง entity->response
    CategoryResponseDTO toResponse(CategoryEntity categoryEntity);

    //แปลงDTO->entity
    @Mapping(target = "id",ignore = true)
    CategoryEntity toEntity(CreateCategoryDTO createCategoryDTO);

    //สำหรับ update entity ที่มีอยู่แล้ว
    @Mapping(target = "id",ignore = true)
    void updateEntityFromDto(UpdateCategoryDTO updateCategoryDTO, @MappingTarget CategoryEntity categoryEntity);

}
