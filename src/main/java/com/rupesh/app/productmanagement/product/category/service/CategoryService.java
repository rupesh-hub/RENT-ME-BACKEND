package com.rupesh.app.productmanagement.product.category.service;

import com.rupesh.app.productmanagement.product.category.model.CategoryRequest;
import com.rupesh.app.productmanagement.product.category.model.CategoryResponse;
import com.rupesh.app.productmanagement.product.category.repository.CategoryRepository;
import com.rupesh.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public GlobalResponse<Void> save(CategoryRequest category) {
        return null;
    }

    @Override
    public GlobalResponse<CategoryResponse> findById(Long id) {
        return null;
    }

    @Override
    public GlobalResponse<CategoryResponse> findByName(String name) {
        return null;
    }

    @Override
    public GlobalResponse<List<CategoryResponse>> findAll(int page, int size) {
        return null;
    }

    @Override
    public GlobalResponse<Void> update(CategoryRequest category, Long id) {
        return null;
    }

    @Override
    public GlobalResponse<Void> delete(Long id) {
        return null;
    }
}
