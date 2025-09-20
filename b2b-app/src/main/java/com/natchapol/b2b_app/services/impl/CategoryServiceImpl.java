package com.natchapol.b2b_app.services.impl;

import com.natchapol.b2b_app.dto.request.CreateCategoryDTO;
import com.natchapol.b2b_app.dto.request.UpdateCategoryDTO;
import com.natchapol.b2b_app.dto.response.CategoryResponseDTO;
import com.natchapol.b2b_app.entity.CategoryEntity;
import com.natchapol.b2b_app.exception.AlreadyExistCategory;
import com.natchapol.b2b_app.exception.CategoryNotFoundException;
import com.natchapol.b2b_app.mapper.CategoryMapper;
import com.natchapol.b2b_app.repository.CategoryProductRepository;
import com.natchapol.b2b_app.services.CategoryProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryProductService {
    @Autowired
    private final CategoryProductRepository categoryProductRepository;


    @Override
    public CategoryResponseDTO createCategory(CreateCategoryDTO createCategoryDTO) {
        Optional<CategoryEntity> categoryName = categoryProductRepository.findByName(createCategoryDTO.getName());
        if (categoryName.isPresent()) {
            throw new AlreadyExistCategory("มีหมวดสินค้านี้แล้วในระบบ");
        } else {
            CategoryEntity category = CategoryMapper.INSTANCE.toEntity(createCategoryDTO);
            CategoryEntity categoryEntity = categoryProductRepository.save(category);
            CategoryResponseDTO categoryResponseDTO = CategoryMapper.INSTANCE.toResponse(categoryEntity);
            return categoryResponseDTO;
        }
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, UpdateCategoryDTO updateCategoryDTO) {
        Optional<CategoryEntity> categoryName = categoryProductRepository.findByName(updateCategoryDTO.getName());
        if (categoryName.isPresent()) {
            throw new AlreadyExistCategory("มีหมวดสินค้านี้แล้วในระบบ");
        } else {
            CategoryEntity category = categoryProductRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
            CategoryMapper.INSTANCE.updateEntityFromDto(updateCategoryDTO, category);
            CategoryEntity categoryEntity = categoryProductRepository.save(category);
            CategoryResponseDTO categoryResponseDTO = CategoryMapper.INSTANCE.toResponse(categoryEntity);
            return categoryResponseDTO;
        }

    }

    @Override
    public void deleteCategory(Long id) {
       CategoryEntity categoryId = categoryProductRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(id));
       if(categoryId != null){
           categoryProductRepository.deleteById(categoryId.getId());
       }
    }

    @Override
    public List<CategoryResponseDTO> readAllCategory() {
        List<CategoryEntity> allCategories = categoryProductRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTO = allCategories.stream().map(category -> CategoryMapper.INSTANCE.toResponse(category)).toList();
        return categoryResponseDTO;
    }
}
