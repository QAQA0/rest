package com.dgsw.sns.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUploadRequest {
    private String content;
    private int postId;
}
