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

import com.example.blogrelationships.models.Author;
import com.example.blogrelationships.records.author.CreateAuthorInput;
import com.example.blogrelationships.records.author.UpdateAuthorInput;
import com.example.blogrelationships.services.AuthorService;

@RequestMapping("blog/author")
@RestController
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody CreateAuthorInput createAuthorInput) {
        Author authorCreated = authorService.createAuthor(createAuthorInput.toAuthor());

        return new ResponseEntity<>(authorCreated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.findAllAuthor();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable UUID id) {
        Optional<Author> author = authorService.findAuthorById(id);

        if (author.isPresent()) {
            return new ResponseEntity<>(author.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Author> upadateAuthor(@PathVariable UUID id,
            @RequestBody UpdateAuthorInput updateAuthorInput) {
        Optional<Author> author = authorService.findAuthorById(id);
        if (author.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author authorToUpdate = author.get();
        authorToUpdate.setName(updateAuthorInput.name());

        Author authorUpdated = authorService.updateAuthor(authorToUpdate);

        return new ResponseEntity<>(authorUpdated, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deteteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
