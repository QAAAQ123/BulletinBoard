package com.example.bulletin_board.repository;

import com.example.bulletin_board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost_PostId(Long postId);
}
