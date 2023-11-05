package com.zelly.encomendas.encomendasZelly.service.usuario;

import com.zelly.encomendas.encomendasZelly.model.enderecoEntity;

public record dadosCadastroUsuario(String nome, Long matricula, String cargo, String login, String password, enderecoEntity endereco) {

}
