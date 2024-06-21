package com.dgsw.sns.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequest {
    private int id;
    private String postTitle;
    private String postContent;
}
