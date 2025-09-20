package com.natchapol.b2b_app.mapper;

import com.natchapol.b2b_app.dto.request.CreateCategoryDTO;
import com.natchapol.b2b_app.dto.request.UpdateCategoryDTO;
import com.natchapol.b2b_app.dto.response.CategoryResponseDTO;
import com.natchapol.b2b_app.entity.CategoryEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T11:54:11+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponseDTO toResponse(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setId( categoryEntity.getId() );
        categoryResponseDTO.setName( categoryEntity.getName() );

        return categoryResponseDTO;
    }

    @Override
    public CategoryEntity toEntity(CreateCategoryDTO createCategoryDTO) {
        if ( createCategoryDTO == null ) {
            return null;
        }

        CategoryEntity.CategoryEntityBuilder categoryEntity = CategoryEntity.builder();

        categoryEntity.name( createCategoryDTO.getName() );

        return categoryEntity.build();
    }

    @Override
    public void updateEntityFromDto(UpdateCategoryDTO updateCategoryDTO, CategoryEntity categoryEntity) {
        if ( updateCategoryDTO == null ) {
            return;
        }

        categoryEntity.setName( updateCategoryDTO.getName() );
    }
}
