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
        List<Pagamento> pagamentos = repository.findAll();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> findById(@PathVariable("id") Integer id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
        return ResponseEntity.ok(pagamento);
    }

    @PostMapping
    public ResponseEntity<Pagamento> save(@RequestBody PagamentoRequestDTO dto) {
        validarPagamentoDTO(dto);

        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(dto.dataPagamento());
        pagamento.setValor(dto.valor());
        pagamento.setMetodo(dto.metodo());
        pagamento.setStatus(dto.status());

        repository.save(pagamento);
        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento com ID " + id + " não encontrado"));

        repository.delete(pagamento);
        return ResponseEntity.noContent().build();
    }

    private void validarPagamentoDTO(PagamentoRequestDTO dto) {
        if (dto.valor() <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }
        if (dto.metodo() == null || dto.metodo().isBlank()) {
            throw new IllegalArgumentException("O método de pagamento é obrigatório.");
        }
    }
}
