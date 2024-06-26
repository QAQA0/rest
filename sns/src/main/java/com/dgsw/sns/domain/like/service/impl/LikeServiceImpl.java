package com.dgsw.sns.domain.like.service.impl;

import com.dgsw.sns.domain.like.domain.Like;
import com.dgsw.sns.domain.like.dto.LikeRegisterRequest;
import com.dgsw.sns.domain.like.repository.LikeRepository;
import com.dgsw.sns.domain.like.service.LikeService;
import com.dgsw.sns.domain.post.domain.Post;
import com.dgsw.sns.domain.post.repository.PostRepository;
import com.dgsw.sns.domain.user.domain.User;
import com.dgsw.sns.domain.user.repository.UserRepository;
import com.dgsw.sns.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserSecurity userSecurity;

    @Override
    public void addLike(LikeRegisterRequest request) {
        User user = userRepository.findById(userSecurity.getUser().getEmail())
                .orElseThrow(RuntimeException::new);
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(RuntimeException::new);

        post.plusPostLikeCount();

        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();

        likeRepository.save(like);
    }

    @Override
    public void removeLike(LikeRegisterRequest request) {
        User user = userRepository.findById(userSecurity.getUser().getEmail())
                .orElseThrow(RuntimeException::new);
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(RuntimeException::new);

        post.minusPostLikeCount();

        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();

        likeRepository.delete(like);
    }
}
