package com.natchapol.b2b_app.services;

import com.natchapol.b2b_app.dto.request.CreateCategoryDTO;
import com.natchapol.b2b_app.dto.request.UpdateCategoryDTO;
import com.natchapol.b2b_app.dto.response.CategoryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryProductService {
    CategoryResponseDTO createCategory(CreateCategoryDTO createCategoryDTO);

    CategoryResponseDTO updateCategory(Long id, UpdateCategoryDTO updateCategoryDTO);

    void deleteCategory(Long id);

    List<CategoryResponseDTO> readAllCategory();
}
