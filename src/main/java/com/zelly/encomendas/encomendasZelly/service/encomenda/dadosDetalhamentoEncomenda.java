package com.zelly.encomendas.encomendasZelly.service.encomenda;

import com.zelly.encomendas.encomendasZelly.model.clienteEntity;
import com.zelly.encomendas.encomendasZelly.model.encomendaEntity;
import com.zelly.encomendas.encomendasZelly.model.produtoEntity;
import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;

import java.time.LocalDateTime;
import java.util.List;

public record dadosDetalhamentoEncomenda(Long id, Status status, LocalDateTime dataPedido, LocalDateTime dataPrevisaoEntrega, LocalDateTime dataEntrega, clienteEntity cliente, List<produtoEntity> produtos, usuarioEntity usuario) {
    public dadosDetalhamentoEncomenda(encomendaEntity encomenda) {
        this(encomenda.getId(), encomenda.getStatus(), encomenda.getDataPedido(), encomenda.getDataPrevisaoEntrega(),  encomenda.getDataEntrega(), encomenda.getClienteEntity(), encomenda.getProdutos(), encomenda.getUsuario() );
    }
}
