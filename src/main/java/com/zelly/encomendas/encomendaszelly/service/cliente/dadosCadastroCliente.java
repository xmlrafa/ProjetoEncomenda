package com.zelly.encomendas.encomendaszelly.service.cliente;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.enderecoEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record dadosCadastroCliente(
        @NotBlank
        String nome,
        @NotBlank
        enderecoEntity endereco,
        List<encomendaEntity> encomendasEntitiesList){

    public dadosCadastroCliente(clienteEntity cliente) {
        this(cliente.getNomeCliente(), cliente.getEndereco(), cliente.getEncomendasEntitiesList());
    }
}