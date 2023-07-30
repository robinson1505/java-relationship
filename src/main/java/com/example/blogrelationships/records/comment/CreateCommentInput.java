package com.example.blogrelationships.records.comment;

import com.example.blogrelationships.models.Author;
import com.example.blogrelationships.models.Comment;
import com.example.blogrelationships.models.Post;

public record CreateCommentInput(String Content,Author author,Post post) {
    public Comment toComment(){
        Comment comment = new Comment();
        comment.setContent(Content);
        comment.setAuthor(author);
        comment.setPost(post);
        return comment;

    }
    
}
