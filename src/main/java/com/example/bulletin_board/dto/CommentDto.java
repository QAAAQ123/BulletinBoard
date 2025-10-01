package com.example.bulletin_board.dto;

import com.example.bulletin_board.entity.Comment;
import com.example.bulletin_board.entity.Post;
import com.example.bulletin_board.entity.ReplyComment;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDto {
    private Long commentId;
    private Long postId;
    private String commentContent;
    private String commentUpdatedAt;
    //25-10-1 추가
    private List<ReplyComment> replyCommentList;

    public Comment toEntity(Post post) {
        return new Comment(this.commentId,post,this.replyCommentList,this.commentContent,this.commentUpdatedAt);
    }
}
