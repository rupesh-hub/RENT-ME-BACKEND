package com.rupesh.app.productmanagement.review.service;

import com.rupesh.app.productmanagement.review.model.ReviewRequest;
import com.rupesh.app.productmanagement.review.model.ReviewResponse;
import com.rupesh.app.util.GlobalResponse;

import java.security.Principal;
import java.util.List;

public interface IReviewService {

    GlobalResponse<Void> create(ReviewRequest request, Principal principal);

    GlobalResponse<ReviewResponse> findById(Long id);

    GlobalResponse<Void> update(ReviewRequest request);

    GlobalResponse<Void> delete(Long id);

    GlobalResponse<List<ReviewResponse>> findAll(int page, int size);

    GlobalResponse<List<ReviewResponse>> findByProductId(Long productId);

    GlobalResponse<List<ReviewResponse>> findByUserId(Long userId);

    GlobalResponse<List<ReviewResponse>> findByProductIdAndUserId(Long productId, Long userId);

}
