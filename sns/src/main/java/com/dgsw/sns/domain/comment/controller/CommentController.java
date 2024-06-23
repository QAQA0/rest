package com.dgsw.sns.domain.comment.controller;

import com.dgsw.sns.domain.comment.dto.CommentUploadRequest;
import com.dgsw.sns.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> addComment(CommentUploadRequest request) {
        commentService.addComment(request);
        return ResponseEntity.ok(request.getContent());
    }
}
