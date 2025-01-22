package com.denisvasilchenko.tms.repository;

import com.denisvasilchenko.tms.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
