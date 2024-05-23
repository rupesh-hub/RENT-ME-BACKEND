package com.rupesh.app.productmanagement.review.resource;

import com.rupesh.app.productmanagement.review.model.ReviewRequest;
import com.rupesh.app.productmanagement.review.model.ReviewResponse;
import com.rupesh.app.productmanagement.review.service.IReviewService;
import com.rupesh.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "product_reviews")
public class ReviewResource {

    private final IReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<Void>> addReview(@RequestBody ReviewRequest review,
                                                          Principal principal) {
        reviewService.create(review, principal);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<List<ReviewResponse>>> getAll(@RequestParam(name="page", defaultValue = "0") int page,
                                                                       @RequestParam(name="size", defaultValue = "10") int size) {
        reviewService.findAll(page, size);
        return ResponseEntity.ok(reviewService.findAll(page, size));
    }


}
