package com.rupesh.app.productmanagement.product.service;

import com.rupesh.app.exception.RentMeException;
import com.rupesh.app.productmanagement.category.repository.CategoryRepository;
import com.rupesh.app.productmanagement.product.entity.Product;
import com.rupesh.app.productmanagement.product.mapper.ProductMapper;
import com.rupesh.app.productmanagement.product.model.Paging;
import com.rupesh.app.productmanagement.product.model.ProductRequest;
import com.rupesh.app.productmanagement.product.model.ProductResponse;
import com.rupesh.app.productmanagement.product.repository.ProductRepository;
import com.rupesh.app.productmanagement.review.comment.mapper.CommentMapper;
import com.rupesh.app.productmanagement.review.mapper.ReviewMapper;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public GlobalResponse<Void> save(ProductRequest request) {

        String categoryName = request.getCategory();
        var category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RentMeException("Category not found by name " + categoryName));

        var product = ProductMapper.toEntity(request);
        product.setCategory(category);
        product.setCreatedDate(LocalDateTime.now());

        productRepository.save(product);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<ProductResponse> findById(Long id) {
        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new RentMeException("Product by " + id + " is not found."));
        return GlobalResponse.success(ProductMapper.toResponse(product));
    }

    @Override
    public GlobalResponse<List<ProductResponse>> findAll(int page, int size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));

        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            var productResponse = ProductMapper.toResponse(product);

            var reviews = reviewRepository.findByProductId(product.getId());

            var reviewResponses = reviews
                    .stream()
                    .map(review -> ReviewResponse
                            .builder()
                            .user(
                                    userRepository.findById(review.getUserId())
                                            .map(UserMapper::toResponse)
                                            .orElseThrow(() -> new RentMeException("User not found by id " + review.getUserId()))
                            )
                            .rating(review.getRating())
                            .updatedAt(review.getLastModifiedDate())
                            .createdAt(review.getCreatedDate())
                            .id(review.getId())
                            .comments(
                                    review.getComments().stream()
                                            .map(CommentMapper::toResponse)
                                            .toList()
                            )
                            .build())
                    .toList();

            productResponse.setReviews(reviewResponses);
            productResponses.add(productResponse);
        }

        return GlobalResponse.success(
                productResponses,
                Paging.builder()
                        .page(page)
                        .size(size)
                        .totalElement(productPage.getTotalElements())
                        .totalPage(productPage.getTotalPages())
                        .first(productPage.isFirst())
                        .last(productPage.isLast())
                        .build()
        );
    }

    @Override
    public GlobalResponse<Void> delete(Long id) {
        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new RentMeException("Product by " + id + " is not found."));
        productRepository.delete(product);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<Void> update(ProductRequest request, Long id) {
        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new RentMeException("Product by " + id + " is not found."));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setLastModifiedDate(LocalDateTime.now());
        productRepository.save(product);
        return GlobalResponse.success();
    }

}
