package com.example.bulletin_board.controller;

import com.example.bulletin_board.dto.CommentDto;
import com.example.bulletin_board.dto.PostDto;
import com.example.bulletin_board.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CommentController {

    @Autowired
    Service service;

    //update comment
    @PutMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto ,@PathVariable Long commentId){
            log.info("comment id:" + commentId + " put request");
            CommentDto updatedDto = service.updateComment(commentDto,commentId);
            return (updatedDto != null) ?
                    ResponseEntity.status(HttpStatus.OK).body(updatedDto):
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //create comment
    @PostMapping("{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Long postId){
        log.info("new comment create request(post id:{})",postId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createComment(commentDto,postId));
    }


    //delete comment
    @Transactional
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId){
        log.info("comment id: {} delete request");
        service.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}



