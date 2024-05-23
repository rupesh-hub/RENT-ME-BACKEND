package com.rupesh.app.productmanagement.review.comment.mapper;

import com.rupesh.app.productmanagement.review.comment.entity.Comment;
import com.rupesh.app.productmanagement.review.comment.model.CommentRequest;
import com.rupesh.app.productmanagement.review.comment.model.CommentResponse;

public class CommentMapper {

    private CommentMapper() {}

    public static Comment toEntity(CommentRequest comment) {
        return null;
    }

    public static CommentResponse toResponse(Comment comment) {
        return CommentResponse
                .builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .commentAt(comment.getCreatedDate())
                .updatedAt(comment.getLastModifiedDate())
                .build();
    }
}
