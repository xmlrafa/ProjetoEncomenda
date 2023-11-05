package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.enderecoEntity;

public record dadosCadastroUsuario(String nome, Long matricula, String cargo, String login, String password, enderecoEntity endereco) {

}
