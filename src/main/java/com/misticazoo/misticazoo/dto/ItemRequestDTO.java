package com.misticazoo.misticazoo.dto;

public record ItemRequestDTO(Integer idCategoria, String nome, String imagemUrl, String descricao, String caracteristicas, String cuidados, String raridade, Integer preco, Integer estoque
) {
}
