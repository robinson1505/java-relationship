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

import com.example.blogrelationships.models.Comment;
import com.example.blogrelationships.records.comment.CreateCommentInput;
import com.example.blogrelationships.records.comment.UpdateCommentInput;
import com.example.blogrelationships.services.CommentService;

@RequestMapping("blog/comments")

public class CommentContoller {
    private CommentService commentService;

    public CommentContoller(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentInput createCommentInput) {
        Comment commentCreated = commentService.createComment(createCommentInput.toComment());

        return new ResponseEntity<>(commentCreated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComment() {
        List<Comment> comments = commentService.findAllComment();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable UUID id) {
        Optional<Comment> comment = commentService.findCommentById(id);

        if (comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Comment> upadateComment(@PathVariable UUID id,
            @RequestBody UpdateCommentInput updateCommentInput) {
        Optional<Comment> post = commentService.findCommentById(id);
        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Comment commentToUpdate = post.get();

        commentToUpdate.setContent(updateCommentInput.content());

        Comment commentUpdated = commentService.updateComment(commentToUpdate);

        return new ResponseEntity<>(commentUpdated, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> detetePost(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
