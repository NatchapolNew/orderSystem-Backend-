package com.natchapol.b2b_app.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProductDTO {
    @NotBlank(message = "Product name is required")
    private String name;
    private String description;

    @Min(value = 1,message = "Product price must is at least 1")
    private BigDecimal price;

    @Min(value = 1, message = "Product stock must is at least 1")
    private Long stock;

    private String imgUrl;
}
