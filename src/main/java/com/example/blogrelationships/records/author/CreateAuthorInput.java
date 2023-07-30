package com.example.blogrelationships.records.author;

import com.example.blogrelationships.models.Author;

public record CreateAuthorInput(String name, String email) {
    public Author toAuthor() {
        Author author = new Author();
        author.setName(name);
        author.setEmail(email);

        return author;

    }

}
