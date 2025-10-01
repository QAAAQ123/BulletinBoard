package com.example.bulletin_board.dto;

import com.example.bulletin_board.entity.Comment;
import com.example.bulletin_board.entity.ReplyComment;
import lombok.*;

@AllArgsConstructor
@Getter
public class ReplyCommentDto {
    private Long replyCommentId;
    private String replyCommentContent;
    private String replyCommentUpdateAt;

    public ReplyComment toEntity() {
        return new ReplyComment(this.replyCommentId,new Comment(),this.replyCommentContent,this.replyCommentUpdateAt);
    }
}
