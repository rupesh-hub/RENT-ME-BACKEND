package com.rupesh.app.productmanagement.review.mapper;

import com.rupesh.app.productmanagement.review.model.ReviewResponse;
import com.rupesh.app.productmanagement.review.entity.Review;
import com.rupesh.app.productmanagement.review.model.ReviewRequest;

public final class ReviewMapper {

    private ReviewMapper() {}

    public static Review toEntity(ReviewRequest request){
       return Review
                .builder()
                .productId(request.getProductId())
                .rating(request.getRating())
                .build();
    }

    public static ReviewResponse toResponse(Review review){
        return null;
    }

}
