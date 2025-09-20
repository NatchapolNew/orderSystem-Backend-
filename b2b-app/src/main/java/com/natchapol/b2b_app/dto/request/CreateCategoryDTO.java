package com.natchapol.b2b_app.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryDTO {
    @NotNull(message = "Category is require")
    private String name;
}
