package com.zelly.encomendas.encomendasZelly.service.cliente;

import com.zelly.encomendas.encomendasZelly.model.clienteEntity;
import com.zelly.encomendas.encomendasZelly.model.enderecoEntity;

public record dadosListagemClientes (Long id, String nomeCLiente, enderecoEntity endereco){
    public dadosListagemClientes(clienteEntity cliente){
        this(cliente.getId(), cliente.getNomeCliente(), cliente.getEndereco());
            }
}
