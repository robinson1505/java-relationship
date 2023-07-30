package com.example.blogrelationships.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.blogrelationships.models.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {

}
