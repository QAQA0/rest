package com.dgsw.sns.domain.post.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_photo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoName;
    private String photoPath;
    private Long photoSize;

    @ManyToOne
    private Post post;

    public void setBoard(Post post) {
        this.post = post;
    }
}
