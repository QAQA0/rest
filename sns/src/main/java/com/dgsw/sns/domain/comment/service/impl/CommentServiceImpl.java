package com.dgsw.sns.domain.comment.service.impl;

import com.dgsw.sns.domain.comment.domain.Comment;
import com.dgsw.sns.domain.comment.dto.CommentUploadRequest;
import com.dgsw.sns.domain.comment.repository.CommentRepository;
import com.dgsw.sns.domain.comment.service.CommentService;
import com.dgsw.sns.domain.post.domain.Post;
import com.dgsw.sns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public void addComment(CommentUploadRequest request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(RuntimeException::new);

        Comment comment = Comment.builder()
                .content(request.getContent())
                .likeCount(0)
                .post(post)
                .build();

        commentRepository.save(comment);
    }
}
