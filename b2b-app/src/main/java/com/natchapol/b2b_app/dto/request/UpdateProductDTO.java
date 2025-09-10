package com.natchapol.b2b_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductDTO {
    @NotBlank(message = "Update product is required ID")
    private Long id;
    private String name;
    private String Description;
    private BigDecimal price;
    private Long Stock;
}
