package com.zelly.encomendas.encomendasZelly.service.encomenda;

import com.zelly.encomendas.encomendasZelly.model.clienteEntity;
import com.zelly.encomendas.encomendasZelly.model.encomendaEntity;
import com.zelly.encomendas.encomendasZelly.model.produtoEntity;
import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;

import java.time.LocalDateTime;

public record dadosListagemEncomendas (Long id, String status, LocalDateTime dataPedido, LocalDateTime dataPrevisaoEntrega, LocalDateTime dataEntrega, clienteEntity cliente, produtoEntity produtos, usuarioEntity usuario){
    public dadosListagemEncomendas(encomendaEntity encomenda){
        this(encomenda.getId(), encomenda.getStatus(), encomenda.getDataPedido(), encomenda.getDataPrevisaoEntrega(), encomenda.getDataEntrega(), encomenda.getClienteEntity(), (produtoEntity) encomenda.getProdutos(), encomenda.getUsuario());
    }
}
