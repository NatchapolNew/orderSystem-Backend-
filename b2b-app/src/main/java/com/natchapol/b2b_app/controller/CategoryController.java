package com.natchapol.b2b_app.controller;

import com.natchapol.b2b_app.dto.request.CreateCategoryDTO;
import com.natchapol.b2b_app.dto.request.UpdateCategoryDTO;
import com.natchapol.b2b_app.dto.response.CategoryResponseDTO;
import com.natchapol.b2b_app.services.CategoryProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private final CategoryProductService categoryProductService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CategoryResponseDTO createCategory(@RequestBody CreateCategoryDTO createCategoryDTO) {
        CategoryResponseDTO category = categoryProductService.createCategory(createCategoryDTO);
        return category;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CategoryResponseDTO updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryDTO updateCategoryDTO){
            CategoryResponseDTO categoryResponseDTO = categoryProductService.updateCategory(id,updateCategoryDTO);
        return categoryResponseDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategory(@PathVariable @Valid Long id){
        categoryProductService.deleteCategory(id);
    }

    @GetMapping
    List<CategoryResponseDTO> readAllCategory(){
        List<CategoryResponseDTO> allCategory = categoryProductService.readAllCategory();
        return allCategory;
    }
}
