package com.example.blogrelationships.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogrelationships.models.Post;
import com.example.blogrelationships.records.post.CreatePostInput;
import com.example.blogrelationships.records.post.UpdatePostInput;
import com.example.blogrelationships.services.PostService;

@RequestMapping("blog/posts")
@RestController

public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostInput createPostInput) {
        Post postCreated = postService.createPost(createPostInput.toPost());

        return new ResponseEntity<>(postCreated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> authors = postService.findAllPost();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable UUID id) {
        Optional<Post> post = postService.findPostById(id);

        if (post.isPresent()) {
            return new ResponseEntity<>(post.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Post> upadatePost(@PathVariable UUID id,
            @RequestBody UpdatePostInput updatePostInput) {
        Optional<Post> post = postService.findPostById(id);
        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post postToUpdate = post.get();
        postToUpdate.setTitle(updatePostInput.title());
        postToUpdate.setContent(updatePostInput.content());

        Post postUpdated = postService.updatePost(postToUpdate);

        return new ResponseEntity<>(postUpdated, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> detetePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
