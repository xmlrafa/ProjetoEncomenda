package com.zelly.encomendas.encomendasZelly.service.usuario;

import com.zelly.encomendas.encomendasZelly.model.enderecoEntity;
import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;

public record dadosDetalhamentoUsuario (Long id, String nome, Long matricula, String cargo, String username, String password, enderecoEntity endereco) {
    public dadosDetalhamentoUsuario(usuarioEntity usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getMatricula(), usuario.getCargo(), usuario.getLogin(), usuario.getPassword(), usuario.getEndereco());
    }
}
