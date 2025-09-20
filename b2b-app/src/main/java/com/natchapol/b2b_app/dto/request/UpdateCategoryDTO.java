package com.natchapol.b2b_app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCategoryDTO {
    @NotNull(message = "Category id is require")
    private Long id;
    private String name;
}
