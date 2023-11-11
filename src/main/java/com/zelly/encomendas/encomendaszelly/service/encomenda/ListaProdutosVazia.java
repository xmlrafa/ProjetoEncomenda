package com.zelly.encomendas.encomendaszelly.service.encomenda;

public class ListaProdutosVazia extends RuntimeException {
    public ListaProdutosVazia(){
    super("A lista de produtos não pode estar vazia.");
    }
}
