package com.example.bulletin_board;

import com.example.bulletin_board.entity.Comment;
import com.example.bulletin_board.entity.Post;
import com.example.bulletin_board.repository.CommentRepository;
import com.example.bulletin_board.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class DataInit {

    @Bean
    public CommandLineRunner initData(PostRepository postRepository, CommentRepository commentRepository) {
        return args -> {
            LocalDateTime now = LocalDateTime.now();
            String nowStr = now.toString();

            Post p1 = new Post(null, "글 제목 1", "내용 1", nowStr,new ArrayList<>());
            Post p2 = new Post(null, "글 제목 2", "내용 2", nowStr,new ArrayList<>());

            Comment c1 = new Comment(null, p1, "댓글 1", nowStr);
            Comment c2 = new Comment(null, p1, "댓글 2", nowStr);
            Comment c3 = new Comment(null, p2, "댓글 3", nowStr);
            Comment c4 = new Comment(null, p2, "댓글 4", nowStr);

            Post post1 = postRepository.save(p1);
            Post post2 = postRepository.save(p2);

            Comment comment1 = commentRepository.save(c1);
            Comment comment2 = commentRepository.save(c2);
            Comment comment3 = commentRepository.save(c3);
            Comment comment4 = commentRepository.save(c4);

            post1.getCommentList().add(comment1);
            post1.getCommentList().add(comment2);
            post2.getCommentList().add(comment3);
            post2.getCommentList().add(comment4);

            postRepository.save(post1);
            postRepository.save(post2);
        };
    }
}
