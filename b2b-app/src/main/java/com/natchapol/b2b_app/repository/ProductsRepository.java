package com.natchapol.b2b_app.repository;

import com.natchapol.b2b_app.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductsEntity,Long> {
}
