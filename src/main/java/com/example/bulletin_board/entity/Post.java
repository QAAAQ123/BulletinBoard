package com.example.bulletin_board.entity;

import com.example.bulletin_board.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = {"commentList"}) // 순환 참조 방지
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "postId")
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

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    public PostDto toDto() {
        return new PostDto(postId, title, content, update_at, commentList);
    }

    public void mergeData(Post post) {
        if (post.title != null) this.title = post.title;
        if (post.content != null) this.content = post.content;
        this.update_at = post.update_at;
    }

    public void addCommentList(List<Comment> comments) {
        this.commentList.addAll(comments);
    }
}