package com.zelly.encomendas.encomendasZelly.service.usuario;

import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;
import com.zelly.encomendas.encomendasZelly.model.enderecoEntity;


public record dadosListagemUsuarios(Long id, String nome, Long matricula, String cargo, String username, String password, enderecoEntity endereco) {
    public dadosListagemUsuarios(usuarioEntity usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getMatricula(), usuario.getCargo(), usuario.getUsername(), usuario.getPassword(), usuario.getEndereco());
    }
}
