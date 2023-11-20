package com.zelly.encomendas.encomendaszelly.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zelly.encomendas.encomendaszelly.service.encomenda.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "encomendas")
@Entity(name = "encomendaEntity")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EncomendaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataPedido;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataPrevisaoEntrega;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("encomendas")
    private ClienteEntity cliente;

    @ManyToMany()
    private List<ProdutoEntity> produtos;

    @ManyToOne
    @JsonIgnoreProperties("encomendas")
    private UsuarioEntity usuario;

       public EncomendaEntity(){
        this.status = Status.ABERTO;
        this.dataPedido = LocalDateTime.now();
        this.dataPrevisaoEntrega = dataPedido.plusDays(1);


    }
    public EncomendaEntity(ClienteEntity cliente, List<ProdutoEntity> produtos){
        this.cliente = cliente;
        this.produtos = produtos;
    }


    public EncomendaEntity(EncomendaEntity encomendaAtualizada) {
           this.id = encomendaAtualizada.getId();
           this.produtos = encomendaAtualizada.getProdutos();
           this.dataEntrega = encomendaAtualizada.getDataEntrega();
           this.dataPedido = encomendaAtualizada.getDataPedido();
           this.usuario = encomendaAtualizada.getUsuario();
           this.status = encomendaAtualizada.getStatus();
           this.dataPrevisaoEntrega = encomendaAtualizada.getDataPrevisaoEntrega();
           this.cliente = encomendaAtualizada.getCliente();

    }
}
