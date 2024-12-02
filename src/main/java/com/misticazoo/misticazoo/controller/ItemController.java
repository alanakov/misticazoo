package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.ItemRequestDTO;
import com.misticazoo.misticazoo.model.Categoria;
import com.misticazoo.misticazoo.model.Item;
import com.misticazoo.misticazoo.repository.CategoriaRepository;
import com.misticazoo.misticazoo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> itens = this.repository.findAll();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable("id") Integer id) {
        Item item = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
        return ResponseEntity.ok(item);
    }


    @PostMapping
    public ResponseEntity<Item> save(@RequestBody ItemRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isEmpty() ||
                dto.preco() == null || dto.estoque() == null ||
                dto.idCategoria() == null) {
            return ResponseEntity.status(400).build();
        }

        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        Item item = new Item();
        item.setNome(dto.nome());
        item.setImagemUrl(dto.imagemUrl());
        item.setDescricao(dto.descricao());
        item.setCaracteristicas(dto.caracteristicas());
        item.setCuidados(dto.cuidados());
        item.setRaridade(dto.raridade());
        item.setPreco(dto.preco());
        item.setEstoque(dto.estoque());
        item.setCategoria(categoria);

        this.repository.save(item);
        return ResponseEntity.ok(item);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Integer id, @RequestBody ItemRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isEmpty() ||
                dto.preco() == null || dto.estoque() == null ||
                dto.idCategoria() == null) {
            return ResponseEntity.status(400).build();
        }

        Item item = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        item.setNome(dto.nome());
        item.setImagemUrl(dto.imagemUrl());
        item.setDescricao(dto.descricao());
        item.setCaracteristicas(dto.caracteristicas());
        item.setCuidados(dto.cuidados());
        item.setRaridade(dto.raridade());
        item.setPreco(dto.preco());
        item.setEstoque(dto.estoque());
        item.setCategoria(categoria);

        this.repository.save(item);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Item item = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
        this.repository.delete(item);
        return ResponseEntity.noContent().build();
    }
}
