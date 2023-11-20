package com.zelly.encomendas.encomendaszelly.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntity {

    /**
     * eu consigo conectar a uma api do correios para
     * quando inserir um CEP o endereço carregar automaticamente? **/
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
}
