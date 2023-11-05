package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.enderecoEntity;
import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;

public record dadosDetalhamentoUsuario (Long id, String nome, Long matricula, String cargo, String username, enderecoEntity endereco) {
    public dadosDetalhamentoUsuario(usuarioEntity usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getMatricula(), usuario.getCargo(), usuario.getLogin(), usuario.getEndereco());
    }
}
