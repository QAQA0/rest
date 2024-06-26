package com.dgsw.sns.domain.post.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_post")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Length(min = 1, max = 50)
    private String title;
    @NotBlank
    @Length(min = 1, max = 500)
    private String content;
    @NotBlank
    private String author;

    private int postLikeCount;
    private int postCommentCount;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void plusPostLikeCount() {
        postLikeCount++;
    }

    public void minusPostLikeCount() {
        postLikeCount--;
    }

    public void plusPostCommentCount() {
        postCommentCount++;
    }

    public void minusPostCommentCount() {
        postCommentCount--;
    }

}
