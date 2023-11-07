package com.zelly.encomendas.encomendaszelly.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.service.encomenda.Status;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosCadastroEncomenda;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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

    public encomendaEntity(dadosCadastroEncomenda dados) {

        this.status = Status.ABERTO;
        this.dataPedido = LocalDateTime.now();
        this.dataPrevisaoEntrega = dataPedido.plus(1, ChronoUnit.DAYS);
        this.clienteEntity = dados.Cliente();
        this.usuario = dados.Usuario();
    }
    public encomendaEntity(){}
    public encomendaEntity(clienteEntity cliente, List<produtoEntity> produtos){
        this.clienteEntity = cliente;
        this.produtos = produtos;
    }



}
