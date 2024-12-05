package com.misticazoo.misticazoo.repository;

import com.misticazoo.misticazoo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
