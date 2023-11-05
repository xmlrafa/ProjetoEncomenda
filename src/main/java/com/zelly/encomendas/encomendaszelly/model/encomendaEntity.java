package com.zelly.encomendas.encomendaszelly.model;


import com.zelly.encomendas.encomendaszelly.service.encomenda.Status;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosCadastroEncomenda;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Table(name = "encomendas")
@Entity(name = "encomendaEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class encomendaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // TODO: 02/11/2023 O status da encomenda deve ser um ENUM
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime dataPedido;
    private LocalDateTime dataPrevisaoEntrega;
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private clienteEntity clienteEntity;

    @OneToMany(mappedBy = "encomenda", cascade = CascadeType.ALL)
    private List<produtoEntity> produtos;

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private usuarioEntity usuario;

    public encomendaEntity(dadosCadastroEncomenda dados) {
        this.status = Status.ABERTO;
        this.dataPedido = LocalDateTime.now();
        this.dataPrevisaoEntrega = dataPedido.plus(1, ChronoUnit.DAYS);
        this.clienteEntity = dados.cliente();
        this.produtos = dados.produto();
        this.usuario = dados.usuario();
    }
}
