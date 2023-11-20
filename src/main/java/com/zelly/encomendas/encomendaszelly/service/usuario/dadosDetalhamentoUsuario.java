package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.EnderecoEntity;
import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;

public record dadosDetalhamentoUsuario (Long id, String nome, Long matricula, String cargo, String username, EnderecoEntity endereco) {
    public dadosDetalhamentoUsuario(UsuarioEntity usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getMatricula(), usuario.getCargo(), usuario.getLogin(), usuario.getEndereco());
    }
}
