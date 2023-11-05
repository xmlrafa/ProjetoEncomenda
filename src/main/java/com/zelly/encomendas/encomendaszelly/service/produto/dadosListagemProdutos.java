package com.zelly.encomendas.encomendaszelly.service.produto;

import com.zelly.encomendas.encomendaszelly.model.produtoEntity;

public record dadosListagemProdutos (Long id, String nome, tamanhoRoupa tamanho, Double valor, int quantidadeEmEstoque) {
    public dadosListagemProdutos(produtoEntity produto){
        this(produto.getId(), produto.getNome(), produto.getTamanho(), produto.getValor(), produto.getQuantidadeEmEstoque());
    }
}
