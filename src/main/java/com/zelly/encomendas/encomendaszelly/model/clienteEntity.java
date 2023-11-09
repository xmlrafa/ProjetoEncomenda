package com.zelly.encomendas.encomendaszelly.model;

import com.zelly.encomendas.encomendaszelly.service.cliente.dadosAtualizacaoCliente;
import com.zelly.encomendas.encomendaszelly.service.cliente.dadosCadastroCliente;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "clientes")
@Entity(name = "clienteEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class clienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;


    @Embedded
    private enderecoEntity endereco;

    public clienteEntity(dadosCadastroCliente dados) {
        this.nomeCliente = dados.nome();
        this.endereco = dados.endereco();
    }

    public void atualizarInformacoes(dadosAtualizacaoCliente dados){
        if (dados.id() != null){
            this.id = dados.id();
        }
        if (dados.nome() != null){
            this.nomeCliente = dados.nome();
        }
        if (this.endereco != null){
            this.endereco = dados.endereco();
        }
    }
}
