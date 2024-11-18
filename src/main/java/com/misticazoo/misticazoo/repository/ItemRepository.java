package com.misticazoo.misticazoo.repository;

import com.misticazoo.misticazoo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
