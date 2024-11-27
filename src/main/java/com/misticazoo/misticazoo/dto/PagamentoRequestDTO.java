package com.misticazoo.misticazoo.dto;

public record PagamentoRequestDTO(String dataPagamento, double valor, String metodo, String status) {
}
