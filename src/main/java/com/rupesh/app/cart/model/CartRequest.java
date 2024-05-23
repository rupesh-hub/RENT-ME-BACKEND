package com.rupesh.app.cart.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {

    private Long productId;
    private int quantity;

}
