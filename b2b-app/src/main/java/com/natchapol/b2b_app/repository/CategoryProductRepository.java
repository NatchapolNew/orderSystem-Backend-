package com.natchapol.b2b_app.repository;

import com.natchapol.b2b_app.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryEntity,Long>{
}
