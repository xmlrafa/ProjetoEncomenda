package com.zelly.encomendas.encomendaszelly.service.encomenda;

import com.zelly.encomendas.encomendaszelly.model.ClienteEntity;
import com.zelly.encomendas.encomendaszelly.model.EncomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.ProdutoEntity;
import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;

import java.time.LocalDateTime;
import java.util.List;

public record dadosDetalhamentoEncomenda(Long id, Status status, LocalDateTime dataPedido, LocalDateTime dataPrevisaoEntrega, LocalDateTime dataEntrega, ClienteEntity cliente, List<ProdutoEntity> produtos, UsuarioEntity usuario) {
    public dadosDetalhamentoEncomenda(EncomendaEntity encomenda) {
        this(encomenda.getId(), encomenda.getStatus(), encomenda.getDataPedido(), encomenda.getDataPrevisaoEntrega(),  encomenda.getDataEntrega(), encomenda.getCliente(), encomenda.getProdutos(), encomenda.getUsuario() );
    }
}
