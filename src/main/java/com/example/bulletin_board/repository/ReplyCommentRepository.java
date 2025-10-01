package com.example.bulletin_board.repository;

import com.example.bulletin_board.entity.ReplyComment;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyCommentRepository extends JpaRepository<ReplyComment,Long> {
    default ReplyComment findByIdOrElseThrow(Long replyCommentId){
        return findById(replyCommentId)
                .orElseThrow(() -> new EntityNotFoundException("Reply Comment can not found"));
    }
}
