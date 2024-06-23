package com.dgsw.sns.domain.like.service;

import com.dgsw.sns.domain.like.dto.LikeRegisterRequest;

public interface LikeService {
    void addLike(LikeRegisterRequest request);
}
