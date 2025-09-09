package com.natchapol.b2b_app.repository;

import com.natchapol.b2b_app.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity,Long> {
}
