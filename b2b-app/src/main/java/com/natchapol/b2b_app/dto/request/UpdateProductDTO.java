package com.natchapol.b2b_app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductDTO {
    @NotNull(message = "Update product is required ID")
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;
    private Long categoryId;
    private String imgUrl;
}
