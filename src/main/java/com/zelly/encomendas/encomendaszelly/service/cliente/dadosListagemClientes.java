package com.zelly.encomendas.encomendaszelly.service.cliente;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.model.enderecoEntity;

public record dadosListagemClientes (Long id, String nomeCLiente, enderecoEntity endereco){
    public dadosListagemClientes(clienteEntity cliente){
        this(cliente.getId(), cliente.getNomeCliente(), cliente.getEndereco());
            }
}
