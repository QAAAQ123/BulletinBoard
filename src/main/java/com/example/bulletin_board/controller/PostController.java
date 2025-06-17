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
public class PostController {

    @Autowired
    private Service service;

    //Show posts
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> showPosts(){
        log.info("posts get request");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showPosts());
    }

    //Show post and comments
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> showPost(@PathVariable Long postId){
        log.info("post and comments get request");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showPost(postId));
    }

    //Create post
    @Transactional
    @PostMapping("/")
    public ResponseEntity<PostDto>  createPost(@RequestBody PostDto postDto){
        log.info("'post' post request");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createPost(postDto));
    }

    //Update post
    @Transactional
    @PutMapping("/{postId}") //patch를 지원하지 않아서 put으로 변경함
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long postId){
        log.info("post id:" + postId + " patch request");
        PostDto updatedDto = service.updatePost(postDto,postId);
        return (updatedDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updatedDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Transactional
    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId){
        service.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
