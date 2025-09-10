package com.natchapol.b2b_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDTO {
    @NotBlank(message = "User name is required")
    private String name;

    @NotBlank(message = "User Password is required")
    private String password;

    @NotBlank(message = "User address is required")
    private String address;

    private String role;
}
