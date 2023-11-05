package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;
import com.zelly.encomendas.encomendaszelly.model.enderecoEntity;


public record dadosListagemUsuarios(Long id, String nome, Long matricula, String cargo, String username, String password, enderecoEntity endereco) {
    public dadosListagemUsuarios(usuarioEntity usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getMatricula(), usuario.getCargo(), usuario.getLogin(), usuario.getPassword(), usuario.getEndereco());
    }
}
