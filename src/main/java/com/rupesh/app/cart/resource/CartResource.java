package com.rupesh.app.cart.resource;

import com.rupesh.app.cart.model.CartRequest;
import com.rupesh.app.cart.model.CartResponse;
import com.rupesh.app.cart.service.ICartService;
import com.rupesh.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Tag(name = "product_carts")
public class CartResource {

    private final ICartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<Void>> add(
            @RequestBody @Valid CartRequest request,
            Principal principal) {
        cartService.add(request, principal);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/by.userId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<List<CartResponse>>> getByUserId(
            Principal principal,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                cartService.getByUserId(page, size, principal)
        );
    }

    @GetMapping("/by.userId/productId/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<CartResponse>> getByUserId(
            @PathVariable Long productId,
            Principal principal
    ) {
        return ResponseEntity.ok(
                cartService.getByProductIdAndUSerId(productId, principal)
        );
    }

}
