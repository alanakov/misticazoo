package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.CategoriaRequestDTO;
import com.misticazoo.misticazoo.model.Categoria;
import com.misticazoo.misticazoo.model.Item;
import com.misticazoo.misticazoo.repository.CategoriaRepository;
import com.misticazoo.misticazoo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categorias = repository.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable("id") Integer id) {
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<Item>> getItensByCategoria(@PathVariable Integer id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        List<Item> itens = itemRepository.findByCategoria(categoria);

        return ResponseEntity.ok(itens);
    }


    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody CategoriaRequestDTO dto) {
        if (dto.nome().isEmpty() || dto.descricao().isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());

        if (dto.imagemUrl() != null && !dto.imagemUrl().trim().isEmpty()) {
            categoria.setImagemUrl(dto.imagemUrl().trim());
        }

        Categoria savedCategoria = repository.save(categoria);

        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoria.setIdCategoria(id);
        Categoria updatedCategoria = repository.save(categoria);
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
