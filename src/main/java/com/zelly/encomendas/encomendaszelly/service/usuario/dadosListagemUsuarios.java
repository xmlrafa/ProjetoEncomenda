package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import com.zelly.encomendas.encomendaszelly.model.EnderecoEntity;


public record dadosListagemUsuarios(Long id, String nome, Long matricula, String cargo, String username, String password, EnderecoEntity endereco) {
    public dadosListagemUsuarios(UsuarioEntity usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getMatricula(), usuario.getCargo(), usuario.getLogin(), usuario.getPassword(), usuario.getEndereco());
    }
}
