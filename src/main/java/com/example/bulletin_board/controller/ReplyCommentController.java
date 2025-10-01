package com.example.bulletin_board.controller;

import com.example.bulletin_board.dto.ReplyCommentDto;
import com.example.bulletin_board.service.ReplyCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//25-10-1 컨트롤러 전체 추가
@RestController
@Slf4j
@RequestMapping("/api/posts/{postId}/comments/{commentId}/")
public class ReplyCommentController {

    final private ReplyCommentService replyCommentService;

    @Autowired
    public ReplyCommentController(ReplyCommentService replyCommentService){
        this.replyCommentService = replyCommentService;
    }

    @PostMapping("replyComments")
    public ResponseEntity<ReplyCommentDto> createReplyComment(
            @PathVariable Long postId,@PathVariable Long commentId,
            @RequestBody ReplyCommentDto replyCommentDto) {

        log.info("new reply comment create request(post id:{},comment id:{})",postId,commentId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(replyCommentService.createReplyComment(postId,commentId,replyCommentDto));
    }

    @PutMapping("replyComments/{replyCommentId}")
    public ResponseEntity<ReplyCommentDto> updateReplyComment(
            @PathVariable Long postId,@PathVariable Long commentId,
            @PathVariable Long replyCommentId,@RequestBody ReplyCommentDto replyCommentDto) {

        log.info("reply comment put request(post id:{},comment id:{},reply comment id:{})",postId,commentId,replyCommentId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(replyCommentService.updateReplyComment(postId,commentId,replyCommentId,replyCommentDto));
    }

    @DeleteMapping("replyComments/{replyCommentId}")
    public ResponseEntity<Void> deleteReplyComment(
            @PathVariable Long postId,@PathVariable Long commentId,
            @PathVariable Long replyCommentId) {

        log.info("reply comment delete request(post id:{},comment id:{},reply comment id:{})",postId,commentId,replyCommentId);
        replyCommentService.deleteReplyComment(postId,commentId,replyCommentId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
