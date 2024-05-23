package com.rupesh.app.productmanagement.product.mapper;

import com.rupesh.app.productmanagement.product.entity.Product;
import com.rupesh.app.productmanagement.product.model.ProductRequest;
import com.rupesh.app.productmanagement.product.model.ProductResponse;

public class ProductMapper {

    private ProductMapper() {
    }

    public static Product toEntity(ProductRequest request) {
        Product product = new Product();

        return product;
    }

    public static ProductResponse toResponse(Product product) {
        return null;
    }
}
