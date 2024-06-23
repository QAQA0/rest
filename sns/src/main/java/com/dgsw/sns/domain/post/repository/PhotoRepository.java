package com.dgsw.sns.domain.post.repository;

import com.dgsw.sns.domain.post.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
