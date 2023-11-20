package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.EnderecoEntity;

public record DadosAtualizacaoUsuario(Long id, String nome, Long matricula, String cargo, String login, String password, EnderecoEntity endereco){
}
