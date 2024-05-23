package com.rupesh.app.productmanagement.review.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rupesh.app.productmanagement.product.model.ProductResponse;
import com.rupesh.app.productmanagement.review.comment.model.CommentResponse;
import com.rupesh.app.productmanagement.review.comment.repository.CommentRepository;
import com.rupesh.app.user.model.UserResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewResponse {

    private Long id;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> comments;
    private UserResponse user;
    private ProductResponse product;

}
