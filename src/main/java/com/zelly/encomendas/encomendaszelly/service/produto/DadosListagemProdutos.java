package com.zelly.encomendas.encomendaszelly.service.produto;

import com.zelly.encomendas.encomendaszelly.model.ProdutoEntity;

public record DadosListagemProdutos(Long id, String nome, tamanhoRoupa tamanho, Double valor, Long quantidadeEmEstoque) {
    public DadosListagemProdutos(ProdutoEntity produto){
        this(produto.getId(), produto.getNome(), produto.getTamanho(), produto.getValor(), produto.getQuantidadeEmEstoque());
    }
}
