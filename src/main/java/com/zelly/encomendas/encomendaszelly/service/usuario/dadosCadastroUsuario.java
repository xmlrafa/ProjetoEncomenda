package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.enderecoEntity;
import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;

public record dadosCadastroUsuario(String nome, Long matricula, String cargo, String login, String password, enderecoEntity endereco) {
    public dadosCadastroUsuario(UsuarioEntity usuario){
        this(usuario.getNome(), usuario.getMatricula(), usuario.getCargo(), usuario.getLogin(), usuario.getPassword(), usuario.getEndereco());
    }
}
