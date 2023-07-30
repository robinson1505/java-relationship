package com.example.blogrelationships.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.blogrelationships.models.Post;
import com.example.blogrelationships.repository.PostRepository;

@Service
public class PostService {
    public PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return this.postRepository.save(post);
    }

    public List<Post> findAllPost() {
        List<Post> posts = new ArrayList<>();
        this.postRepository.findAll().forEach(posts::add);
        return posts;
    }

    public Optional<Post> findPostById(UUID id) {
        return this.postRepository.findById(id);
    }

    public Post updatePost(Post authorToUpdate) {
        return postRepository.save(authorToUpdate);
    }

    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }

}
