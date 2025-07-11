package com.example.bulletin_board.entity;

import com.example.bulletin_board.dto.CommentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(exclude = {"post"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "comment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "commentId")
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonBackReference // post 필드 직렬화 제외
    private Post post;

    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_updated_at")
    private String commentUpdatedAt;

    public void mergeCommentData(Comment comment) {
        if(comment.commentContent != null) this.commentContent = comment.commentContent;
        if(comment.commentUpdatedAt != null) this.commentUpdatedAt = comment.commentUpdatedAt;
    }

    public CommentDto toDto() {
        return new CommentDto(this.commentId,this.post.getPostId(),this.commentContent,this.commentUpdatedAt);
    }

}