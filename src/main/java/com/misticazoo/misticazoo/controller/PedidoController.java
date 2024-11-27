package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.PedidoRequestDTO;
import com.misticazoo.misticazoo.model.Pedido;
import com.misticazoo.misticazoo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = this.repository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public Pedido findById(@PathVariable("id") Integer id) throws IllegalAccessException {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Pedido não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody PedidoRequestDTO dto) {
        if (dto.dataPedido().isEmpty() || dto.status().isEmpty() || dto.valorTotal() == 0) {
            return ResponseEntity.status(400).build();
        }

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.parse(dto.dataPedido()));
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());

        this.repository.save(pedido);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws IllegalAccessException {
        Pedido pedido = this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Pedido não encontrado"));
        this.repository.delete(pedido);
        return ResponseEntity.noContent().build();
    }
}
