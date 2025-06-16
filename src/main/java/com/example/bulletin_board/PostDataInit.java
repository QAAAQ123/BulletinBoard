package com.example.bulletin_board;

import com.example.bulletin_board.entity.Post;
import com.example.bulletin_board.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


@Configuration
public class PostDataInit {
    @Bean
    public CommandLineRunner PostInit(PostRepository postRepository) {
        LocalDateTime now = LocalDateTime.now();
        String nowStr = now.toString();
        return args -> {
            postRepository.save(new Post(null, "글 제목 1", "내용 1", nowStr));
            postRepository.save(new Post(null, "글 제목 2", "내용 2", nowStr));
        };
    }
}