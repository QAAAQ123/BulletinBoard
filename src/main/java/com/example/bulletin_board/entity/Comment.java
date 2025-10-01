package com.example.bulletin_board.entity;

import com.example.bulletin_board.common.CurrentTime;
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

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = {"post"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "comment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "commentId")
public class Comment {
    //comment(comment_id(PK),post_id(FK),comment_content,comment_update_at)
    //commentId,post,replyCommentList,commentContent,commentUpdateAt
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonBackReference // post 필드 직렬화 제외
    private Post post;

    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY,orphanRemoval = true)
    //null허용하지 않고 초기화 할때 빈 리스트로
    private List<ReplyComment> replyCommentList = new ArrayList<>();

    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_updated_at")
    private String commentUpdatedAt;

    public void mergeCommentData(Comment comment) {
        boolean modified = false;
        if(comment.commentContent != null) {
            this.commentContent = comment.commentContent;
            modified = true;
        }
        if(comment.commentUpdatedAt != null) {
            this.commentUpdatedAt = comment.commentUpdatedAt;
            modified = true;
        }
        if(modified)
            this.commentUpdatedAt = CurrentTime.getCurrentTime();
    }

    public CommentDto toDto() {
        return new CommentDto(this.commentId,this.post.getPostId(),this.commentContent,this.commentUpdatedAt,this.replyCommentList);
    }

}