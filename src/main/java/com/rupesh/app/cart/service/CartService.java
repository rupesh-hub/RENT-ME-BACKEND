package com.rupesh.app.cart.service;

import com.rupesh.app.cart.entity.Cart;
import com.rupesh.app.cart.model.CartRequest;
import com.rupesh.app.cart.model.CartResponse;
import com.rupesh.app.cart.repository.CartRepository;
import com.rupesh.app.exception.RentMeException;
import com.rupesh.app.productmanagement.product.mapper.ProductMapper;
import com.rupesh.app.productmanagement.product.model.Paging;
import com.rupesh.app.productmanagement.product.repository.ProductRepository;
import com.rupesh.app.user.repository.UserRepository;
import com.rupesh.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public GlobalResponse<Void> add(CartRequest request, Principal principal) {
        String username = principal.getName();
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RentMeException("user not exists with username " + username));

        var cart = Cart
                .builder()
                .userId(user.getId())
                .productId(request.getProductId())
                .createdDate(LocalDateTime.now())
                .quantity(request.getQuantity())
                .build();

        cartRepository.save(cart);

        return GlobalResponse.success();

    }

    @Override
    public GlobalResponse<List<CartResponse>> getByUserId(
            int page,
            int size,
            Principal principal) {

        String username = principal.getName();
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RentMeException("user not exists with username " + username));

        Page<Cart> cartPage = cartRepository.findByUserId(user.getId(), PageRequest.of(page, size));

        var response = cartPage.getContent()
                .stream()
                .map(item -> CartResponse
                        .builder()
                        .id(item.getId())
                        .createdAt(item.getCreatedDate())
                        .updatedAt(item.getLastModifiedDate())
                        .quantity(item.getQuantity())
                        .product(
                                productRepository
                                        .findById(item.getProductId())
                                        .map(ProductMapper::toResponse)
                                        .orElseThrow(() -> new RentMeException("No product exists with id " + item.getProductId()))
                        )
                        .build())
                .toList();

        return GlobalResponse.success(response,
                Paging.builder()
                        .page(page)
                        .size(size)
                        .totalElement(cartPage.getTotalElements())
                        .totalPage(cartPage.getTotalPages())
                        .first(cartPage.isFirst())
                        .last(cartPage.isLast())
                        .build()
        );
    }

    @Override
    public GlobalResponse<CartResponse> getByProductIdAndUSerId(Long productId, Principal principal) {

        String username = principal.getName();
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RentMeException("user not exists with username " + username));

        var response = cartRepository.findByProductIdAndUserId(productId, user.getId())
                .map(item -> CartResponse
                        .builder()
                        .id(item.getId())
                        .createdAt(item.getCreatedDate())
                        .updatedAt(item.getLastModifiedDate())
                        .quantity(item.getQuantity())
                        .product(
                                productRepository
                                        .findById(item.getProductId())
                                        .map(ProductMapper::toResponse)
                                        .orElseThrow(() -> new RentMeException("No product exists with id " + item.getProductId()))
                        )
                        .build())
                .orElseThrow(() -> new RentMeException("No item found."));

        return GlobalResponse.success(response);
    }
}
