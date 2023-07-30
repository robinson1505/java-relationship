package com.example.blogrelationships.records.post;

import com.example.blogrelationships.models.Author;
import com.example.blogrelationships.models.Post;

public record CreatePostInput(String title,Author author,String content) {

public Post toPost(){
    Post post = new Post();
    post.setTitle(title);
    post.setAuthor(author);
    post.setContent(content);

    return post;
}

    
}
