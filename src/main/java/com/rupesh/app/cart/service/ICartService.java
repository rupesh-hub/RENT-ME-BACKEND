package com.rupesh.app.cart.service;

import com.rupesh.app.cart.model.CartRequest;
import com.rupesh.app.cart.model.CartResponse;
import com.rupesh.app.util.GlobalResponse;

import java.security.Principal;
import java.util.List;

public interface ICartService {

    GlobalResponse<Void> add(CartRequest request, Principal principal);

    GlobalResponse<List<CartResponse>> getByUserId(
            int page,
            int size,
            Principal principal);

    GlobalResponse<CartResponse> getByProductIdAndUSerId(Long productId, Principal principal);

}
