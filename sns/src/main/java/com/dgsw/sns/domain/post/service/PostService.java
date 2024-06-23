package com.dgsw.sns.domain.post.service;

import com.dgsw.sns.domain.post.domain.Post;
import com.dgsw.sns.domain.post.dto.page.PageableRequest;
import com.dgsw.sns.domain.post.dto.post.PostCreateRequest;
import com.dgsw.sns.domain.post.dto.post.PostDTO;
import com.dgsw.sns.domain.post.dto.post.PostUpdateRequest;
import org.springframework.data.domain.Page;

public interface PostService {
    void create(PostCreateRequest request);
    Page<PostDTO> getPosts(PageableRequest request);
    PostDTO getById(int id);
    PostDTO update(PostUpdateRequest request);
    void delete(int id);

    default PostDTO entityToDto(Post entity) {
        return PostDTO.builder()
                .title(entity.getTitle())
                .content(entity.getContent())
                .Author(entity.getAuthor())
                .postLikeCount(entity.getPostLikeCount())
                .postCommentCount(entity.getPostCommentCount())
                .build();
    }
}
