package com.rupesh.app.productmanagement.product.repository;

import com.rupesh.app.productmanagement.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
