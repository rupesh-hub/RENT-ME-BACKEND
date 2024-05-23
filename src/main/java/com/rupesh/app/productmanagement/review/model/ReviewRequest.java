package com.rupesh.app.productmanagement.review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    private int rating;
    private Long productId;
    private String comment;

}
