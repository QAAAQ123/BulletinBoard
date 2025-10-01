package com.example.bulletin_board.repository;

import com.example.bulletin_board.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyCommentRepository extends JpaRepository<ReplyComment,Long> {
}
