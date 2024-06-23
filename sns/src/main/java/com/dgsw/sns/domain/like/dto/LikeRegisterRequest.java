package com.dgsw.sns.domain.like.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRegisterRequest {
    private String userEmail;
    private int postId;
}
