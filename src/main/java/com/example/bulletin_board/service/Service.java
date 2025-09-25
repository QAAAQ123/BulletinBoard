package com.example.bulletin_board.service;

import com.example.bulletin_board.dto.CommentDto;
import com.example.bulletin_board.dto.PostDto;
import com.example.bulletin_board.entity.Comment;
import com.example.bulletin_board.entity.Post;
import com.example.bulletin_board.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bulletin_board.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Slf4j
public class Service {

    final private PostRepository postRepository;
    final private CommentRepository commentRepository;

    @Autowired
    public Service(PostRepository postRepository,CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<PostDto> showPosts() {
        List<Post> postList =  postRepository.findAll();
        List<PostDto> postDtoList = postList.stream()
                .map(Post -> Post.toDto())
                .collect(Collectors.toList());
        log.info("read posts\nnumber of posts: "+postList.size());
        return postDtoList;
    }

    public PostDto createPost(PostDto postDto) {
        Post post = postDto.toEntity();
        Post createTarget= postRepository.save(post);
        log.info("created post\ntitle: "+post.getTitle()+"\ncontent: "+post.getContent()+"\nupdated at: "+post.getUpdateAt());
        return createTarget.toDto();
    }

    public PostDto updatePost(PostDto postDto,Long postId) {
        Post post = postDto.toEntity();
        if(!post.getPostId().equals(postId))
            return null;
        Post updateTarget = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with postId"));
        updateTarget.mergeData(post);
        Post changedPost = postRepository.save(updateTarget);
        log.info("updated post\ntitle: "+updateTarget.getTitle()+"\ncontent: "+updateTarget.getContent()+"\nupdated at: "+updateTarget.getUpdateAt());
        return changedPost.toDto();
    }

    public void deletePost(Long postId) {
        log.info("delete post (postId:{})",postId);
        postRepository.deleteById(postId);
    }

    public PostDto showPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with postId"));
        List<Comment> commentList = commentRepository.findByPost_PostId(postId);
        log.info("read post and comment(postId:{})",postId);

        post.addCommentList(commentList);
        return post.toDto();
    }

    public CommentDto updateComment(CommentDto commentDto, Long commentId) {
        log.info("requested comment content:{}, updated at:{}", commentDto.getCommentContent(), commentDto.getCommentUpdatedAt());
        if (!commentDto.getCommentId().equals(commentId))
            return null;
        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        Post post = target.getPost();
        Comment comment = commentDto.toEntity(post);
        target.mergeCommentData(comment);
        Comment changedComment = commentRepository.save(target);
        log.info("updated comment\ncontent: {}\nupdated at: {}", changedComment.getCommentContent(), changedComment.getCommentUpdatedAt());
        return changedComment.toDto();
    }

    public CommentDto createComment(CommentDto commentDto,Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("entity not found"));
        Comment comment = commentDto.toEntity(post);
        log.info("created comment\ncontent: {}\nupdated at: {}",comment.getCommentContent(),comment.getCommentUpdatedAt());
        Comment createdComment = commentRepository.save(comment);
        return createdComment.toDto();
    }

    public void deleteComment(Long commnetId){
        commentRepository.deleteById(commnetId);
    }

}
