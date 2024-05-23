package com.rupesh.app.productmanagement.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rupesh.app.productmanagement.review.model.ReviewResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private Long id;
    private String name;
    private String category;
    private double price;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String[] images;
    private List<ReviewResponse> reviews;

}
