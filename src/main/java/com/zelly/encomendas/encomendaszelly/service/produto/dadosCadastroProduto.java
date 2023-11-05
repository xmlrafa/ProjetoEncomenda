package com.zelly.encomendas.encomendaszelly.service.produto;

import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record dadosCadastroProduto(
        @NotBlank
        String nome,
        @NotNull
        tamanhoRoupa tamanho,
        @NotBlank
        Double valor,
        int quantidadeEmEstoque) {
    public dadosCadastroProduto(produtoEntity produto) {
        this(produto.getNome(), produto.getTamanho(), produto.getValor(), produto.getQuantidadeEmEstoque());
    }
}
