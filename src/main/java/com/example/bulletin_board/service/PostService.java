package com.example.bulletin_board.service;

import com.example.bulletin_board.dto.PostDto;
import com.example.bulletin_board.entity.Post;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bulletin_board.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {
    @Autowired
    private PostRepository postRepository;

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
        log.info("created post\ntitle: "+post.getTitle()+"\ncontent: "+post.getContent()+"\nupdated at: "+post.getUpdate_at());
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
        log.info("updated post\ntitle: "+updateTarget.getTitle()+"\ncontent: "+updateTarget.getContent()+"\nupdated at: "+updateTarget.getUpdate_at());
        return changedPost.toDto();
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
