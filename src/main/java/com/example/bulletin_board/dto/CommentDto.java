package com.example.bulletin_board.dto;

import com.example.bulletin_board.entity.Comment;
import com.example.bulletin_board.entity.Post;
import lombok.*;

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

    public Comment toEntity(Post post) {
        return new Comment(this.commentId,post,this.commentContent,this.commentUpdatedAt);
    }
}
