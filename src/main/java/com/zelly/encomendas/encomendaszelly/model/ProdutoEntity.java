package com.zelly.encomendas.encomendaszelly.model;

import com.zelly.encomendas.encomendaszelly.service.produto.DadosAtualizacaoProduto;
import com.zelly.encomendas.encomendaszelly.service.produto.DadosCadastroProduto;
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
public class ProdutoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private tamanhoRoupa tamanho;
    private Double valor;
    private Long quantidadeEmEstoque;

   /** @ManyToOne
    @JoinColumn(name = "encomenda_id")
    private encomendaEntity encomenda;
**/
    public ProdutoEntity(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.tamanho = dados.tamanho();
        this.valor = dados.valor();
        this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
    }

    public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
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
        if(dados.quantidadeEstoque() >= 0) {
            this.quantidadeEmEstoque = dados.quantidadeEstoque();
        }
    }
}
