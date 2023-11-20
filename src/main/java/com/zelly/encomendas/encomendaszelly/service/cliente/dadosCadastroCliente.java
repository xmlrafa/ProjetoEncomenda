package com.zelly.encomendas.encomendaszelly.service.cliente;

import com.zelly.encomendas.encomendaszelly.model.ClienteEntity;
import com.zelly.encomendas.encomendaszelly.model.EnderecoEntity;
import jakarta.validation.constraints.NotBlank;

public record dadosCadastroCliente(
        @NotBlank
        String nome,
        @NotBlank
        EnderecoEntity endereco){

    public dadosCadastroCliente(ClienteEntity cliente) {
        this(cliente.getNomeCliente(), cliente.getEndereco());
    }
}