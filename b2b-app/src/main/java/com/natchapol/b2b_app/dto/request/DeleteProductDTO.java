package com.natchapol.b2b_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteProductDTO {
    @NotBlank(message = "Delete product is required ID")
    private Long id;
}
