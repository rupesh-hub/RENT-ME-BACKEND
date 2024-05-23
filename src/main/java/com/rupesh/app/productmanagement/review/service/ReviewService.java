package com.rupesh.app.productmanagement.review.service;

import com.rupesh.app.exception.RentMeException;
import com.rupesh.app.productmanagement.product.mapper.ProductMapper;
import com.rupesh.app.productmanagement.product.model.Paging;
import com.rupesh.app.productmanagement.product.repository.ProductRepository;
import com.rupesh.app.productmanagement.review.comment.entity.Comment;
import com.rupesh.app.productmanagement.review.comment.mapper.CommentMapper;
import com.rupesh.app.productmanagement.review.entity.Review;
import com.rupesh.app.productmanagement.review.model.ReviewRequest;
import com.rupesh.app.productmanagement.review.model.ReviewResponse;
import com.rupesh.app.productmanagement.review.repository.ReviewRepository;
import com.rupesh.app.user.mapper.UserMapper;
import com.rupesh.app.user.repository.UserRepository;
import com.rupesh.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public GlobalResponse<Void> create(ReviewRequest request, Principal principal) {

        var username = principal.getName();
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RentMeException("User not found with email: " + username));

        var review = Review
                .builder()
                .productId(request.getProductId())
                .createdDate(LocalDateTime.now())
                .rating(request.getRating())
                .userId(user.getId())
                .comments(Arrays.asList(
                        Comment
                                .builder()
                                .comment(request.getComment())
                                .createdDate(LocalDateTime.now())
                                .build()
                ))
                .build();
        reviewRepository.save(review);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<ReviewResponse> findById(Long id) {
        var review = reviewRepository.findById(id)
                .orElseThrow(() -> new RentMeException("Review not found with id: " + id));
        var userId = review.getUserId();
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RentMeException("User not found with id: " + userId));

        var productId = review.getProductId();
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new RentMeException("Product not found with id: " + productId));

        return GlobalResponse.success(
                ReviewResponse
                        .builder()
                        .id(review.getId())
                        .rating(review.getRating())
                        .createdAt(review.getCreatedDate())
                        .updatedAt(review.getLastModifiedDate())
                        .user(UserMapper.toResponse(user))
                        .comments(review.getComments().stream().map(CommentMapper::toResponse).toList())
                        .product(ProductMapper.toResponse(product))
                        .build()
        );
    }

    @Override
    public GlobalResponse<Void> update(ReviewRequest request) {
        return null;
    }

    @Override
    public GlobalResponse<Void> delete(Long id) {
        return null;
    }

    @Override
    public GlobalResponse<List<ReviewResponse>> findAll(int page, int size) {

        Page<Review> reviewPage = reviewRepository.findAll(PageRequest.of(page, size));
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : reviewPage.getContent()) {

            var user = userRepository.findById(review.getUserId())
                    .orElseThrow(() -> new RentMeException("User not found with id: " + review.getUserId()));

            var userResponse = UserMapper.toResponse(user);

            var productId = review.getProductId();
            var product = productRepository.findById(productId)
                    .orElseThrow(() -> new RentMeException("Product not found with id: " + productId));

            var productResponse = ProductMapper.toResponse(product);
            var comments = review.getComments()
                    .stream()
                    .map(CommentMapper::toResponse)
                    .toList();

            reviewResponses
                    .add(
                            ReviewResponse
                                    .builder()
                                    .id(review.getId())
                                    .rating(review.getRating())
                                    .createdAt(review.getCreatedDate())
                                    .updatedAt(review.getLastModifiedDate())
                                    .comments(comments)
                                    .user(userResponse)
                                    .product(productResponse)
                                    .build()
                    );

        }

        var paging = Paging
                .builder()
                .page(page)
                .size(size)
                .totalPage(reviewPage.getTotalPages())
                .totalElement(reviewPage.getTotalElements())
                .first(reviewPage.isFirst())
                .last(reviewPage.isLast())
                .build();

        return GlobalResponse.success(reviewResponses, paging);
    }

    @Override
    public GlobalResponse<List<ReviewResponse>> findByProductId(Long productId) {
        return null;
    }

    @Override
    public GlobalResponse<List<ReviewResponse>> findByUserId(Long userId) {
        return null;
    }

    @Override
    public GlobalResponse<List<ReviewResponse>> findByProductIdAndUserId(Long productId, Long userId) {
        return null;
    }
}
