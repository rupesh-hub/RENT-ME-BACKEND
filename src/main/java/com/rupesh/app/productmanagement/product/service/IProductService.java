package com.rupesh.app.productmanagement.product.service;

import com.rupesh.app.productmanagement.product.model.ProductRequest;
import com.rupesh.app.productmanagement.product.model.ProductResponse;
import com.rupesh.app.util.GlobalResponse;

import java.util.List;

public interface IProductService {

    GlobalResponse<Void> save(ProductRequest request);
    GlobalResponse<ProductResponse> findById(Long id);
    GlobalResponse<List<ProductResponse>> findAll(
            int page, int size
    );
    GlobalResponse<Void> delete(Long id);

    GlobalResponse<Void> update(ProductRequest request, Long id);
}
