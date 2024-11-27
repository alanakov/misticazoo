package com.misticazoo.misticazoo.dto;

public record ItemPedidoRequestDTO(Integer idPedido, Integer idItem, Integer quantidade, double precoTotal) {
}
