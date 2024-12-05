package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.ItemPedidoRequestDTO;
import com.misticazoo.misticazoo.model.ItemPedido;
import com.misticazoo.misticazoo.model.Pedido;
import com.misticazoo.misticazoo.model.Item;
import com.misticazoo.misticazoo.repository.ItemPedidoRepository;
import com.misticazoo.misticazoo.repository.PedidoRepository;
import com.misticazoo.misticazoo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> findById(@PathVariable Integer id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ItemPedido não encontrado"));
        return ResponseEntity.ok(itemPedido);
    }

    @PostMapping
    public ResponseEntity<ItemPedido> save(@RequestBody ItemPedidoRequestDTO dto) {
        if (dto.quantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Pedido pedido = pedidoRepository.findById(dto.idPedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        Item item = itemRepository.findById(dto.idItem())
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        ItemPedido itemPedido = new ItemPedido(pedido, item, dto.quantidade());
        itemPedido.setPrecoTotal(item.getPreco() * dto.quantidade());

        itemPedidoRepository.save(itemPedido);
        return ResponseEntity.ok(itemPedido);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> update(@PathVariable Integer id, @RequestBody ItemPedidoRequestDTO dto) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não encontrado"));

        itemPedido.setQuantidade(dto.quantidade());
        itemPedido.setPrecoTotal(itemPedido.getItem().getPreco() * dto.quantidade());

        itemPedidoRepository.save(itemPedido);
        return ResponseEntity.ok(itemPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não encontrado"));

        itemPedidoRepository.delete(itemPedido);
        return ResponseEntity.noContent().build();
    }
}
