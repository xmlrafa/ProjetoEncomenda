package com.zelly.encomendas.encomendaszelly.service.cliente;

import com.zelly.encomendas.encomendaszelly.model.ClienteEntity;
import com.zelly.encomendas.encomendaszelly.model.EnderecoEntity;

public record dadosListagemClientes (Long id, String nomeCLiente, EnderecoEntity endereco){
    public dadosListagemClientes(ClienteEntity cliente){
        this(cliente.getId(), cliente.getNomeCliente(), cliente.getEndereco());
            }
}
