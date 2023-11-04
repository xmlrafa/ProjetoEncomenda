package com.zelly.encomendas.encomendasZelly.service.encomenda;

import com.zelly.encomendas.encomendasZelly.model.clienteEntity;
import com.zelly.encomendas.encomendasZelly.model.produtoEntity;
import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;

import java.time.LocalDateTime;
import java.util.List;

public record dadosCadastroEncomenda (
        Status status, LocalDateTime dataPedido, LocalDateTime dataPrevisaoEntrega, clienteEntity cliente, List<produtoEntity> produto, usuarioEntity usuario) {
}
