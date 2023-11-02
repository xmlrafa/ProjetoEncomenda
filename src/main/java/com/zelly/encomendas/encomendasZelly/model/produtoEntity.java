package com.zelly.encomendas.encomendasZelly.model;

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
    private String tamanho;
    private String valor;
    private int quantidadeEmEstoque;


}
