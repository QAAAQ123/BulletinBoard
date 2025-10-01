package com.example.bulletin_board.entity;

import com.example.bulletin_board.common.CurrentTime;
import com.example.bulletin_board.dto.ReplyCommentDto;
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
    @Column(name = "reply_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyCommentId;

    @JoinColumn(name = "comment_id")
    @ManyToOne
    private Comment comment;

    @Column(name = "reply_comment_content")
    private String replyCommentContent;

    @Column(name = "reply_comment_update_at")
    private String replyCommentUpdateAt;

    public ReplyCommentDto toDto() {
        return new ReplyCommentDto(this.replyCommentId, this.replyCommentContent, this.replyCommentUpdateAt);
    }

    public void mergeReplyCommentEntity(ReplyComment clientInputReplyCommentEntity) {
        boolean modified = false;
        if (clientInputReplyCommentEntity.replyCommentContent != null) {
            this.replyCommentContent = clientInputReplyCommentEntity.replyCommentContent;
            modified = true;
        }
        if (clientInputReplyCommentEntity.replyCommentUpdateAt != null) {
            this.replyCommentUpdateAt = clientInputReplyCommentEntity.replyCommentUpdateAt;
            modified = true;
        }
        if(modified)
            this.replyCommentUpdateAt = CurrentTime.getCurrentTime();

    }

}
