package com.example.blogrelationships.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.blogrelationships.models.Comment;
import com.example.blogrelationships.repository.CommentRepository;

@Service
public class CommentService {

    public CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    public List<Comment> findAllComment() {
        List<Comment> comments = new ArrayList<>();
        this.commentRepository.findAll().forEach(comments::add);
        return comments;
    }

    public Optional<Comment> findCommentById(UUID id) {
        return this.commentRepository.findById(id);
    }

    public Comment updateComment(Comment commentToUpdate) {
        return commentRepository.save(commentToUpdate);
    }

    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }

}
