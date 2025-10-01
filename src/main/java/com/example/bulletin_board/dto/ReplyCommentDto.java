package com.example.bulletin_board.dto;

import com.example.bulletin_board.entity.Comment;
import lombok.*;

@AllArgsConstructor
@Getter
public class ReplyCommentDto {
    private Long replyCommentId;
    private String replyCommentContent;
    private String replyCommentUpdateAt;
}
