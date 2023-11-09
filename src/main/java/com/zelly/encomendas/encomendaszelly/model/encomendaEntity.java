package com.zelly.encomendas.encomendaszelly.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zelly.encomendas.encomendaszelly.service.encomenda.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Table(name = "encomendas")
@Entity(name = "encomendaEntity")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class encomendaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime dataPedido;
    private LocalDateTime dataPrevisaoEntrega;
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("encomendas")
    private clienteEntity clienteEntity;

    @ManyToMany()
    private List<produtoEntity> produtos;

    @ManyToOne
    private usuarioEntity usuario;

       public encomendaEntity(){
        this.status = Status.ABERTO;
        this.dataPedido = LocalDateTime.now();
        this.dataPrevisaoEntrega = dataPedido.plus(1, ChronoUnit.DAYS);
    }
    public encomendaEntity(clienteEntity cliente, List<produtoEntity> produtos){
        this.clienteEntity = cliente;
        this.produtos = produtos;
    }


    public encomendaEntity(encomendaEntity encomendaAtualizada) {
           this.id = encomendaAtualizada.getId();
           this.produtos = encomendaAtualizada.getProdutos();
           this.dataEntrega = encomendaAtualizada.getDataEntrega();
           this.dataPedido = encomendaAtualizada.getDataPedido();
           this.usuario = encomendaAtualizada.getUsuario();
           this.status = encomendaAtualizada.getStatus();
           this.dataPrevisaoEntrega = encomendaAtualizada.getDataPrevisaoEntrega();
           this.clienteEntity = encomendaAtualizada.getClienteEntity();

    }
}
