package com.rupesh.app.productmanagement.product.category.service;

import com.rupesh.app.productmanagement.product.category.model.CategoryRequest;
import com.rupesh.app.productmanagement.product.category.model.CategoryResponse;
import com.rupesh.app.util.GlobalResponse;

import java.util.List;

public interface ICategoryService {

    GlobalResponse<Void> save(CategoryRequest category);
    GlobalResponse<CategoryResponse> findById(Long id);
    GlobalResponse<CategoryResponse> findByName(String name);
    GlobalResponse<List<CategoryResponse>> findAll(int page, int size);
    GlobalResponse<Void> update(CategoryRequest category, Long id);
    GlobalResponse<Void> delete(Long id);


}
