package com.dgsw.sns.domain.post.service.impl;

import com.dgsw.sns.domain.post.domain.Post;
import com.dgsw.sns.domain.post.dto.PageableRequest;
import com.dgsw.sns.domain.post.dto.PostCreateRequest;
import com.dgsw.sns.domain.post.dto.PostDTO;
import com.dgsw.sns.domain.post.dto.PostUpdateRequest;
import com.dgsw.sns.domain.post.repository.PostRepository;
import com.dgsw.sns.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void create(PostCreateRequest request) {
        Post post = Post.builder()
                .title(request.getPostTitle())
                .content(request.getPostContent())
                .author(null)
                .postLikeCount(0)
                .postCommentCount(0)
                .build();

        postRepository.save(post);
    }

    @Override
    public Page<PostDTO> getPosts(PageableRequest request) {
        Pageable pageable = request.getPageable(Sort.by("id").descending());
        List<Post> entityList = postRepository.findAll();
        List<PostDTO> dtoList = new ArrayList<>();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), entityList.size());

        entityList.forEach(entity ->
            dtoList.add(entityToDto(entity))
        );

        return new PageImpl<>(dtoList.subList(start, end), pageable, entityList.size());
    }

    @Override
    public PostDTO getById(int id) {
        Post post = postRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return PostDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .Author(post.getAuthor())
                .postLikeCount(post.getPostLikeCount())
                .postCommentCount(post.getPostCommentCount())
                .build();
    }

    @Override
    public PostDTO update(PostUpdateRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(RuntimeException::new);

        post.update(request.getPostTitle(), request.getPostContent());
        postRepository.save(post);

        return PostDTO.builder()
                .id(post.getId())
                .title(request.getPostTitle())
                .content(request.getPostContent())
                .Author(post.getAuthor())
                .postLikeCount(post.getPostLikeCount())
                .postCommentCount(post.getPostCommentCount())
                .build();
    }

    @Override
    public void delete(int id) {
        postRepository.deleteById(id);
    }
}
