package com.dgsw.sns.domain.post.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private String Author;
    private int postLikeCount;
    private int postCommentCount;
}
