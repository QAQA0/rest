package com.dgsw.sns.domain.comment.repository;

import com.dgsw.sns.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
