package com.example.bulletin_board;

import com.example.bulletin_board.entity.Comment;
import com.example.bulletin_board.entity.Post;
import com.example.bulletin_board.repository.CommentRepository;
import com.example.bulletin_board.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInit {

    @Bean
    public CommandLineRunner initData(PostRepository postRepository, CommentRepository commentRepository) {
        return args -> {
            LocalDateTime now = LocalDateTime.now();
            String nowStr = now.toString();

            Post post1 = postRepository.save(new Post(null, "글 제목 1", "내용 1", nowStr));
            Post post2 = postRepository.save(new Post(null, "글 제목 2", "내용 2", nowStr));

            commentRepository.save(new Comment(null, post1, "댓글 1", nowStr));
            commentRepository.save(new Comment(null, post1, "댓글 2", nowStr));
            commentRepository.save(new Comment(null, post2, "댓글 3", nowStr));
            commentRepository.save(new Comment(null, post2, "댓글 4", nowStr));
        };
    }
}
