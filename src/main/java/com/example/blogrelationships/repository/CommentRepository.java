package com.example.blogrelationships.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.blogrelationships.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, UUID> {

}
