package com.dgsw.sns.domain.comment.service;

import com.dgsw.sns.domain.comment.domain.Comment;
import com.dgsw.sns.domain.comment.dto.CommentUploadRequest;

public interface CommentService {
    void addComment(CommentUploadRequest request);
}
