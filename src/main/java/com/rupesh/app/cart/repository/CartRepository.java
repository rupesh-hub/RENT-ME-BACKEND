package com.rupesh.app.cart.repository;

import com.rupesh.app.cart.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByProductIdAndUserId(Long productId, Long userId);
    Page<Cart> findByUserId(Long userId, Pageable pageable);

}
