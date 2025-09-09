package com.natchapol.b2b_app.repository;

import com.natchapol.b2b_app.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrdersEntity,Long> {
}
