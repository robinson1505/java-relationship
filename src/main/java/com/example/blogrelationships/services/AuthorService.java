package com.example.blogrelationships.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.blogrelationships.models.Author;
import com.example.blogrelationships.repository.AuthorRepository;

@Service
public class AuthorService {
    public AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    public List<Author> findAllAuthor() {
        List<Author> authors = new ArrayList<>();
        this.authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    public Optional<Author> findAuthorById(UUID id) {
        return this.authorRepository.findById(id);
    }

    public Author updateAuthor(Author authorToUpdate) {
        return authorRepository.save(authorToUpdate);
    }

    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }

}