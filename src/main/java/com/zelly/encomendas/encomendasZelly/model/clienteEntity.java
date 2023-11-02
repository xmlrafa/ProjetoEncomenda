package com.zelly.encomendas.encomendasZelly.model;

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
    @OneToMany(mappedBy = "clienteEntity", cascade = CascadeType.ALL)
    private List<encomendaEntity> encomendasEntitiesList;

    @Embedded
    private enderecoEntity endereco;

}
