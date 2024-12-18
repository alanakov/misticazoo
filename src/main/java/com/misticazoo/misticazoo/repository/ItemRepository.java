package com.misticazoo.misticazoo.repository;

import com.misticazoo.misticazoo.model.Categoria;
import com.misticazoo.misticazoo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByCategoria(Categoria categoria);

}
