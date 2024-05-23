package com.rupesh.app.productmanagement.category.mapper;

import com.rupesh.app.productmanagement.category.entity.Category;
import com.rupesh.app.productmanagement.category.model.CategoryRequest;
import com.rupesh.app.productmanagement.category.model.CategoryResponse;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static Category toEntity(CategoryRequest request) {
        return Category
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public static CategoryResponse toResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedDate())
                .lastModifiedDate(category.getLastModifiedDate())
                .build();
    }


}
