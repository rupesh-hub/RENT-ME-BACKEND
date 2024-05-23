package com.rupesh.app.productmanagement.product.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotEmpty(message = "Product name is required.")
    @NotBlank(message = "Product name is required.")
    private String name;

    @NotEmpty(message = "Product category is required.")
    @NotBlank(message = "Product category is required.")
    private String category;

    private double price;

    private String description;

}
