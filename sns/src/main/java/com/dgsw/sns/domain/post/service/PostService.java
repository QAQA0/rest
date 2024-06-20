package com.dgsw.sns.domain.post.service;

import com.dgsw.sns.domain.post.dto.PostCreateRequest;

public interface PostService {
    void create(PostCreateRequest request);

}
