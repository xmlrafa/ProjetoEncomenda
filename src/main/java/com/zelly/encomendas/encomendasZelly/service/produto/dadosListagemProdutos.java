package com.zelly.encomendas.encomendasZelly.service.produto;

import com.zelly.encomendas.encomendasZelly.model.produtoEntity;

public record dadosListagemProdutos (Long id, String nome, tamanhoRoupa tamanho, Double valor, int quantidadeEmEstoque) {
    public dadosListagemProdutos(produtoEntity produto){
        this(produto.getId(), produto.getNome(), produto.getTamanho(), produto.getValor(), produto.getQuantidadeEmEstoque());
    }
}
