package com.rupesh.app.productmanagement.category.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    @NotEmpty(message = "Category name is required.")
    @NotBlank(message = "Category name is required.")
    private String name;
    private String description;

}
