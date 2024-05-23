package com.rupesh.app.cart.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rupesh.app.productmanagement.product.model.ProductResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {

    private Long id;
    private int quantity;
    private ProductResponse product;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
