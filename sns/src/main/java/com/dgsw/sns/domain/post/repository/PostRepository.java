package com.dgsw.sns.domain.post.repository;

import com.dgsw.sns.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
