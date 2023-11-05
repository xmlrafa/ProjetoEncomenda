package com.zelly.encomendas.encomendaszelly.model;

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

    @ManyToOne
    @JoinColumn(name = "encomenda_id")
    private encomendaEntity encomenda;

    public produtoEntity(dadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.tamanho = dados.tamanho();
        this.valor = dados.valor();
        this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
    }
}
