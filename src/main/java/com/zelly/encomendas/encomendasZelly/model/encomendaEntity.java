package com.zelly.encomendas.encomendasZelly.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private String status;
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
}
