package com.dgsw.sns.domain.post.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_post")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotBlank
    @Length(min = 1, max = 50)
    private String Title;
    @NotBlank
    @Length(min = 1, max = 500)
    private String Content;
    @NotBlank
    private String Author;

    private int postLikeCount;
    private int postCommentCount;
}
