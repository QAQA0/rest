package com.dgsw.sns.domain.post.domain;

import com.dgsw.sns.domain.comment.domain.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "tbl_post")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @NotBlank
    @Length(min = 1, max = 50)
    private String postTitle;
    @NotBlank
    @Length(min = 1, max = 500)
    private String postContent;
    @NotBlank
    private String postAuthor;

    private String postImageName;
    private String postImagePath;

    private int postLikeCount;
    private int postCommentCount;
}
