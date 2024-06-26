package com.dgsw.sns.domain.comment.service.impl;

import com.dgsw.sns.domain.comment.domain.Comment;
import com.dgsw.sns.domain.comment.dto.CommentUploadRequest;
import com.dgsw.sns.domain.comment.repository.CommentRepository;
import com.dgsw.sns.domain.comment.service.CommentService;
import com.dgsw.sns.domain.post.domain.Post;
import com.dgsw.sns.domain.post.repository.PostRepository;
import com.dgsw.sns.domain.user.domain.User;
import com.dgsw.sns.domain.user.repository.UserRepository;
import com.dgsw.sns.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;

    @Override
    public void addComment(CommentUploadRequest request) {
        User user = userRepository.findById(userSecurity.getUser().getEmail())
                .orElseThrow(RuntimeException::new);
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(RuntimeException::new);
        
        post.plusPostCommentCount();

        Comment comment = Comment.builder()
                .content(request.getContent())
                .likeCount(0)
                .user(user)
                .post(post)
                .build();

        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
        if(comment.getUser().getEmail().equals(userSecurity.getUser().getEmail())) {
            Post post = comment.getPost();
            post.minusPostCommentCount();
            
            commentRepository.deleteById(commentId);
        }
        throw new RuntimeException("댓글 작성자가 아닙니다.");
    }
}
