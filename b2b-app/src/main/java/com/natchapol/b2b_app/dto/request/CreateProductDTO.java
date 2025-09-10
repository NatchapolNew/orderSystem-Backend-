package com.natchapol.b2b_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProductDTO {
    private String name;
    private String Description;
    private BigDecimal price;
    private Long Stock;
}
