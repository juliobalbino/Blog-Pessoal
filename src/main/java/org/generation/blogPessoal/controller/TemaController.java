package org.generation.blogPessoal.controller;


import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

    @Autowired
    private TemaRepository repository;

    @GetMapping
    public ResponseEntity<List<Tema>> getAll() {

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(resposta -> ResponseEntity .ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PostMapping
    public ResponseEntity<Tema> postTema(@RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }

    @PutMapping
    public ResponseEntity<Tema> putTema(@RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTema(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
