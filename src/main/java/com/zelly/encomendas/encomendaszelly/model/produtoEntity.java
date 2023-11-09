package com.zelly.encomendas.encomendaszelly.model;

import com.zelly.encomendas.encomendaszelly.service.produto.dadosAtualizacaoProduto;
import com.zelly.encomendas.encomendaszelly.service.produto.dadosCadastroProduto;
import com.zelly.encomendas.encomendaszelly.service.produto.tamanhoRoupa;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "produto")
@Entity(name = "produtoEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class produtoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    // TODO: 02/11/2023  o tamanho deve ser um enum
    @Enumerated(EnumType.STRING)
    private tamanhoRoupa tamanho;
    private Double valor;
    private int quantidadeEmEstoque;

   /** @ManyToOne
    @JoinColumn(name = "encomenda_id")
    private encomendaEntity encomenda;
**/
    public produtoEntity(dadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.tamanho = dados.tamanho();
        this.valor = dados.valor();
        this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
    }

    public void atualizarInformacoes(dadosAtualizacaoProduto dados) {
        if(dados.id() != null){
            this.id = dados.id();
        }
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.tamanho() != null){
            this.tamanho = dados.tamanho();
        }
        if(dados.valor() != null){
            this.valor = dados.valor();
        }
        this.quantidadeEmEstoque = dados.quantidadeEstoque();

    }
}
