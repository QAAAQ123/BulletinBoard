package com.example.bulletin_board.repository;

import com.example.bulletin_board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
