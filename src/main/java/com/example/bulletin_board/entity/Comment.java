package com.example.bulletin_board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_updated_at")
    private String commentUpdatedAt;
}
