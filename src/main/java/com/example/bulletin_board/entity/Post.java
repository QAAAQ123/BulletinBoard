package com.example.bulletin_board.entity;

import com.example.bulletin_board.common.CurrentTime;
import com.example.bulletin_board.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = {"commentList"}) // 순환 참조 방지
@Getter
@Setter
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
    private String updateAt;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    public PostDto toDto() {
        return new PostDto(postId, title, content, updateAt, commentList);
    }

    public void mergeData(Post post) {
        boolean modified = false;
        if (post.title != null) {
            this.title = post.title;
            modified = true;
        }
        if (post.content != null) {
            this.content = post.content;
            modified = true;
        }
        if(modified)
            this.updateAt = CurrentTime.getCurrentTime();
    }

    public void addCommentList(List<Comment> comments) {
        this.commentList.addAll(comments);
    }
}