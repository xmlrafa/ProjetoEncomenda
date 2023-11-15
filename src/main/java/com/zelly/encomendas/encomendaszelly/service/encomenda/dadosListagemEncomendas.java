package com.zelly.encomendas.encomendaszelly.service.encomenda;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;

import java.time.LocalDateTime;

public record dadosListagemEncomendas (Long id, Status status, LocalDateTime dataPedido, LocalDateTime dataPrevisaoEntrega, LocalDateTime dataEntrega, clienteEntity cliente, produtoEntity produtos, usuarioEntity usuario){
    public dadosListagemEncomendas(encomendaEntity encomenda){
        this(encomenda.getId(), encomenda.getStatus(), encomenda.getDataPedido(), encomenda.getDataPrevisaoEntrega(), encomenda.getDataEntrega(), encomenda.getCliente(), (produtoEntity) encomenda.getProdutos(), encomenda.getUsuario());
    }
}
