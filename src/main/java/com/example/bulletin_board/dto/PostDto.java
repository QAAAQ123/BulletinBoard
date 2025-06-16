package com.example.bulletin_board.dto;

import com.example.bulletin_board.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;

@AllArgsConstructor //toDto() 때문
@Getter //patch시 어느 글 수정했는지 id가 필요하기 때문
public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private String update_at;

    public Post toEntity() {
        return new Post(postId,title,content,update_at);
    }
}
