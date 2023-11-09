package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.enderecoEntity;
import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;

public record dadosCadastroUsuario(String nome, Long matricula, String cargo, String login, String password, enderecoEntity endereco) {
    public dadosCadastroUsuario(usuarioEntity usuario){
        this(usuario.getNome(), usuario.getMatricula(), usuario.getCargo(), usuario.getLogin(), usuario.getPassword(), usuario.getEndereco());
    }
}
