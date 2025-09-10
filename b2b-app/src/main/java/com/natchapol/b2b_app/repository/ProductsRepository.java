package com.natchapol.b2b_app.repository;

import com.natchapol.b2b_app.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity,Long> {
    Optional<ProductsEntity> findByName(String name);
    List<ProductsEntity> findByNameIsContainingIgnoreCase(String productName);
}
