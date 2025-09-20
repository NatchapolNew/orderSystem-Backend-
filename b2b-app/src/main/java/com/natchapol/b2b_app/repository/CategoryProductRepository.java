package com.natchapol.b2b_app.repository;

import com.natchapol.b2b_app.entity.CategoryEntity;
import com.natchapol.b2b_app.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryProductRepository extends JpaRepository<CategoryEntity,Long>{
    Optional<CategoryEntity> findByName(String name);
}
