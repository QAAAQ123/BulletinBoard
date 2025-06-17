package com.example.bulletin_board.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDto {
    private Long commentId;
    private Long postId;
    private String commentContnet;
    private String commentUpdatedAt;
}
