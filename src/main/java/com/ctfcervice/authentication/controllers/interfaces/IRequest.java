package com.ctfcervice.authentication.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping
public interface IRequest<T> {

    @GetMapping("/")
    Iterable<T> getAll();

    @GetMapping("/{id}")
    Optional<T> getById(@PathVariable long id);

    @PostMapping("/")
    ResponseEntity<?> postObject(@RequestBody T object);

    @PutMapping("/{id}")
    ResponseEntity<?> updateObject(@PathVariable long id, @RequestBody T object);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteObject(@PathVariable long id);
}
