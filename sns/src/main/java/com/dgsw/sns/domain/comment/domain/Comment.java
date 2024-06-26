package com.dgsw.sns.domain.comment.domain;

import com.dgsw.sns.domain.post.domain.Post;
import com.dgsw.sns.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_comment")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    private int likeCount;

    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
}
