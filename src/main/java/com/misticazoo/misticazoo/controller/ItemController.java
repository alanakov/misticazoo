package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.ItemRequestDTO;
import com.misticazoo.misticazoo.model.Item;
import com.misticazoo.misticazoo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = this.repository.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public Item findById(@PathVariable("id") Integer id) throws IllegalAccessException {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Item não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody ItemRequestDTO dto) {
        if (dto.nome().isEmpty() || dto.descricao().isEmpty() || dto.preco() == null || dto.estoque() == null) {
            return ResponseEntity.status(400).build();
        }

        Item item = new Item();
        item.setNome(dto.nome());
        item.setDescricao(dto.descricao());
        item.setPreco(dto.preco());
        item.setEstoque(dto.estoque());

        this.repository.save(item);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws IllegalAccessException {
        Item item = this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Item não encontrado"));
        this.repository.delete(item);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Integer id, @RequestBody ItemRequestDTO dto) throws IllegalAccessException {
        if (dto.nome().isEmpty() || dto.descricao().isEmpty() || dto.preco() == null || dto.estoque() == null) {
            return ResponseEntity.status(400).build();
        }

        Item item = this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Item não encontrado"));

        item.setNome(dto.nome());

        this.repository.save(item);
        return ResponseEntity.ok(item);
    }
}
