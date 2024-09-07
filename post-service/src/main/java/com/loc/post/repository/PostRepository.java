package com.loc.post.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.loc.post.entity.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByUserId(String userId);
}
