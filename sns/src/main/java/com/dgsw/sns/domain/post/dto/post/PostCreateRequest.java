package com.dgsw.sns.domain.post.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    private String postTitle;
    private String postContent;
}
