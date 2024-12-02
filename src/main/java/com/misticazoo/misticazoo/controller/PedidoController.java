package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.PedidoRequestDTO;
import com.misticazoo.misticazoo.model.Pedido;
import com.misticazoo.misticazoo.model.ItemPedido;
import com.misticazoo.misticazoo.repository.PedidoRepository;
import com.misticazoo.misticazoo.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = repository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable("id") Integer id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody PedidoRequestDTO dto) {

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.parse(dto.dataPedido()));
        pedido.setStatus(dto.status());

        // Calcular o valor total do pedido com base nos itens
        double valorTotal = calcularValorTotal(dto.idPedido());
        pedido.setValorTotal(valorTotal);

        repository.save(pedido);
        return ResponseEntity.ok(pedido);
    }

    // Deletar um pedido pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido com não encontrado"));

        repository.delete(pedido);
        return ResponseEntity.noContent().build();
    }

    // auxilia o calculo do valor total do pedido baseado nos itens associados
    private double calcularValorTotal(Integer idPedido) {
        // recupera os itens do pedido
        List<ItemPedido> itensPedido = itemPedidoRepository.findAll().stream()
                .filter(itemPedido -> itemPedido.getPedido().getIdPedido().equals(idPedido))
                .collect(Collectors.toList());

        // soma os valores totais de cada item
        return itensPedido.stream()
                .mapToDouble(itemPedido -> itemPedido.getPrecoTotal())
                .sum();
    }
}
