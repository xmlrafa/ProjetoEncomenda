package com.zelly.encomendas.encomendaszelly.service.encomenda;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;

import java.time.LocalDateTime;
import java.util.List;

public record dadosCadastroEncomenda (
        Status status, LocalDateTime dataPedido, LocalDateTime dataPrevisaoEntrega, clienteEntity cliente, List<produtoEntity> produto, usuarioEntity usuario) {
}
