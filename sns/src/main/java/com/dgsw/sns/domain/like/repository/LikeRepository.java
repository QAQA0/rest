package com.dgsw.sns.domain.like.repository;

import com.dgsw.sns.domain.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
