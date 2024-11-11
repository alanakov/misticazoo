package com.misticazoo.misticazoo.repository;

import com.misticazoo.misticazoo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
