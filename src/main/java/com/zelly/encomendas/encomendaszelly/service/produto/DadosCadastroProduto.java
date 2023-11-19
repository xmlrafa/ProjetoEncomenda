package com.zelly.encomendas.encomendaszelly.service.produto;

import com.zelly.encomendas.encomendaszelly.model.ProdutoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
        @NotBlank
        String nome,
        @NotNull
        tamanhoRoupa tamanho,
        @NotNull
        Double valor,
        Long quantidadeEmEstoque) {
    public DadosCadastroProduto(ProdutoEntity produto) {
        this(produto.getNome(), produto.getTamanho(), produto.getValor(), produto.getQuantidadeEmEstoque());
    }
}
