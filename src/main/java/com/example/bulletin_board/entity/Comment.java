package com.example.bulletin_board.entity;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String comment_content;
    @Column
    private String comment_updated_at;
}
