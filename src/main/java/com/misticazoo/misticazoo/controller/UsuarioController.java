package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.UsuarioRequestDTO;
import com.misticazoo.misticazoo.model.Usuario;
import com.misticazoo.misticazoo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = this.repository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable("id") Integer id) throws IllegalAccessException {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Usuário não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioRequestDTO dto) {
        if (dto.nome().isEmpty() || dto.email().isEmpty() || dto.senha().isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        this.repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws IllegalAccessException {
        Usuario usuario = this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Usuário não encontrado"));
        this.repository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
