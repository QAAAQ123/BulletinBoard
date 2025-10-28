package com.example.bulletin_board.service;

import com.example.bulletin_board.common.CurrentTime;
import com.example.bulletin_board.dto.ReplyCommentDto;
import com.example.bulletin_board.entity.Comment;
import com.example.bulletin_board.entity.ReplyComment;
import com.example.bulletin_board.repository.CommentRepository;
import com.example.bulletin_board.repository.ReplyCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyCommentService {

    final private ReplyCommentRepository replyCommentRepository;
    final private CommentRepository commentRepository;

    @Autowired
    public ReplyCommentService(ReplyCommentRepository replyCommentRepository,CommentRepository commentRepository){
        this.replyCommentRepository = replyCommentRepository;
        this.commentRepository = commentRepository;
    }

    //dto:id,content,updateAt
    //entity: id,comment Entity,content,updateAt

    public ReplyCommentDto createReplyComment(Long postId, Long commentId, ReplyCommentDto replyCommentDto) {
        if(replyCommentDto == null)//예외 처리
            throw new IllegalArgumentException("CommentReplyDto는 null일 수 없습니다.");

        if(replyCommentDto.getReplyCommentContent() == null || replyCommentDto.getReplyCommentContent().trim().isEmpty())//예외 처리
            throw new IllegalArgumentException("대댓글 내용은 필수입니다.");

        ReplyComment inputReplyCommentEntity = replyCommentDto.toEntity();
        inputReplyCommentEntity.setReplyCommentUpdateAt(CurrentTime.getCurrentTime());
        inputReplyCommentEntity.setComment(commentRepository.findByIdOrElseThrow(commentId));

        ReplyComment savedReplyCommentEntity = replyCommentRepository.save(inputReplyCommentEntity);
        return savedReplyCommentEntity.toDto();
    }

    public ReplyCommentDto updateReplyComment(Long postId, Long commentId, Long replyCommentId, ReplyCommentDto replyCommentDto) {
        if(replyCommentDto == null)//예외 처리
            throw new IllegalArgumentException("CommentReplyDto는 null일 수 없습니다.");

        if(replyCommentDto.getReplyCommentContent() == null || replyCommentDto.getReplyCommentContent().trim().isEmpty())//예외 처리
            throw new IllegalArgumentException("대댓글 내용은 필수입니다.");

        ReplyComment updateTargetReplyCommentEntity = replyCommentRepository.findByIdOrElseThrow(replyCommentId);
        Comment comment = updateTargetReplyCommentEntity.getComment();
        updateTargetReplyCommentEntity.mergeReplyCommentEntity(replyCommentDto.toEntity(comment));

        replyCommentRepository.save(updateTargetReplyCommentEntity);
        return updateTargetReplyCommentEntity.toDto();
    }

    public void deleteReplyComment(Long postId, Long commentId, Long replyCommentId) {
        ReplyComment replyComment = replyCommentRepository.findByIdOrElseThrow(commentId);
        //orphanRemoval로 comment측 데이터 제거
        replyCommentRepository.deleteById(replyCommentId);
    }
}
