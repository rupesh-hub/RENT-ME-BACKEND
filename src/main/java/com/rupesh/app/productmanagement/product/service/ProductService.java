package com.rupesh.app.productmanagement.product.service;

import com.rupesh.app.productmanagement.product.model.ProductRequest;
import com.rupesh.app.productmanagement.product.model.ProductResponse;
import com.rupesh.app.productmanagement.product.repository.ProductRepository;
import com.rupesh.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService  implements IProductService{

    private final ProductRepository productRepository;


    @Override
    public GlobalResponse<Void> save(ProductRequest request) {
        return null;
    }

    @Override
    public GlobalResponse<ProductResponse> findById(Long id) {
        return null;
    }

    @Override
    public GlobalResponse<List<ProductResponse>> findAll(int page, int size) {
        return null;
    }

    @Override
    public GlobalResponse<Void> delete(Long id) {
        return null;
    }

    @Override
    public GlobalResponse<Void> update(ProductRequest request, Long id) {
        return null;
    }

}
