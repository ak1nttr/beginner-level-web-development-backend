package com.app.ak1n.tatar.repository;

import com.app.ak1n.tatar.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Optional<Long> userId);


}
