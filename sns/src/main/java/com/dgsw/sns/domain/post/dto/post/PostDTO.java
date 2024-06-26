package com.dgsw.sns.domain.post.dto.post;

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
    private String author;
    private int postLikeCount;
    private int postCommentCount;
}
