package com.example.bulletin_board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="reply_comment")
public class ReplyComment {
    /*
    reply_comment 테이블 스키마:reply_comment(reply_comment_id(PK),reply_comment_content,reply_comment_update_at,comment_id(FK))
     */
    @Id
    @Column(name="reply_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyCommentId;

    @JoinColumn(name="comment_id")
    @ManyToOne
    private Comment comment;

    @Column(name="reply_comment_content")
    private String replyCommentContent;

    @Column(name="reply_comment_update_at")
    private String replyCommentUpdateAt;
}
