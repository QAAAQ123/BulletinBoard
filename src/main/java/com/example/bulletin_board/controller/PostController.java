package com.example.bulletin_board.controller;

import com.example.bulletin_board.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.bulletin_board.service.Service;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class PostController {

    final private Service service;

    @Autowired
    public PostController(Service service){
        this.service = service;
    }

    //Show posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> showPosts(){
        log.info("posts get request");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showPosts());
    }

    //Show post and commentsg
    //25-10-1 로그에 대댓글 추가
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> showPost(@PathVariable Long postId){
        log.info("post and comments and reply comments get request");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showPost(postId));
    }

    //Create post
    @Transactional
    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        log.info("'post' post request");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createPost(postDto));
    }

    //Update post
    @Transactional
    @PutMapping("/posts/{postId}") //patch를 지원하지 않아서 put으로 변경함
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long postId){
        log.info("post id:{} put request", postId);
        PostDto updatedDto = service.updatePost(postDto,postId);
        return (updatedDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updatedDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Transactional
    @DeleteMapping("posts/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId){
        log.info("post id:" + postId + " delete request");
        service.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
