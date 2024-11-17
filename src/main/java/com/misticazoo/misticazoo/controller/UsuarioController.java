package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.UsuarioRequestDTO;
import com.misticazoo.misticazoo.model.Usuario;
import com.misticazoo.misticazoo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public List<Usuario> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable("id") Integer id) throws IllegalAccessException {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Usuário não encontrado"));
    }

    @PostMapping
    public Usuario save(@RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        return this.repository.save(usuario);
    }
}
