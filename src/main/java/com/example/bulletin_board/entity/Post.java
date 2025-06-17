package com.example.bulletin_board.entity;

import com.example.bulletin_board.dto.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.List;

@Entity
@ToString
@Getter //patch service에서 dto와 id값 확인 등...
@NoArgsConstructor //JPA 때문
@AllArgsConstructor //toEntity() 메소드 때문
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String title;
    @Column
    private String content;
    @Column(name = "update_at")
    private String update_at;

    @OneToMany
    @JoinColumn
    private List<Comment> commentList;


    public PostDto toDto() {
        return new PostDto(postId,title,content,update_at,commentList);
    }

    public void mergeData(Post post) {
        if(post.title != null) this.title = post.title;
        if(post.content != null) this.content = post.content;
        this.update_at = post.update_at;
    }
}
