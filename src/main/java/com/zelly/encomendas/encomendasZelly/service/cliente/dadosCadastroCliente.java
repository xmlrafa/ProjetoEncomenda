package com.zelly.encomendas.encomendasZelly.service.cliente;

import com.zelly.encomendas.encomendasZelly.model.clienteEntity;
import com.zelly.encomendas.encomendasZelly.model.encomendaEntity;
import com.zelly.encomendas.encomendasZelly.model.enderecoEntity;
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