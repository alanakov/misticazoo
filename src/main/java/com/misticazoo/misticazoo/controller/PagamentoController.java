package com.misticazoo.misticazoo.controller;

import com.misticazoo.misticazoo.dto.PagamentoRequestDTO;
import com.misticazoo.misticazoo.model.Pagamento;
import com.misticazoo.misticazoo.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository repository;

    @GetMapping
    public ResponseEntity<List<Pagamento>> findAll() {
        List<Pagamento> pagamentos = this.repository.findAll();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public Pagamento findById(@PathVariable("id") Integer id) throws IllegalAccessException {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Pagamento não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Pagamento> save(@RequestBody PagamentoRequestDTO dto) {
        if (dto.dataPagamento().isEmpty() || dto.valor() == null || dto.metodo().isEmpty() || dto.status().isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(dto.dataPagamento());
        pagamento.setValor(dto.valor());
        pagamento.setMetodo(dto.metodo());
        pagamento.setStatus(dto.status());

        this.repository.save(pagamento);
        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws IllegalAccessException {
        Pagamento pagamento = this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Pagamento não encontrado"));
        this.repository.delete(pagamento);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> update(@PathVariable Integer id, @RequestBody PagamentoRequestDTO dto) throws IllegalAccessException {
        if (dto.dataPagamento().isEmpty() || dto.valor() == null || dto.metodo().isEmpty() || dto.status().isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Pagamento pagamento = this.repository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Pagamento não encontrado"));

        pagamento.setNome(dto.nome());

        this.repository.save(pagamento);
        return ResponseEntity.ok(pagamento);
    }
}
