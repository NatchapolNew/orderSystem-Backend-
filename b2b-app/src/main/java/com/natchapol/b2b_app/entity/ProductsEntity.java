package com.natchapol.b2b_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "products")
public class ProductsEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<OrderItemsEntity> orderItems =new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
