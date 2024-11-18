package com.misticazoo.misticazoo.dto;

public record PagamentoRequestDTO(String dataPagamento, Integer valor, String metodo, String status) {
}
