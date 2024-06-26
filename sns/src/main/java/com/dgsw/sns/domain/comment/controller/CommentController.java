package com.dgsw.sns.domain.comment.controller;

import com.dgsw.sns.domain.comment.dto.CommentUploadRequest;
import com.dgsw.sns.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody CommentUploadRequest request) {
        commentService.addComment(request);
        return ResponseEntity.ok(request.getContent());
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
