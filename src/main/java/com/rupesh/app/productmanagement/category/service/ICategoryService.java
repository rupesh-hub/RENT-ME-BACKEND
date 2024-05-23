package com.rupesh.app.productmanagement.category.service;

import com.rupesh.app.productmanagement.category.model.CategoryRequest;
import com.rupesh.app.productmanagement.category.model.CategoryResponse;
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
