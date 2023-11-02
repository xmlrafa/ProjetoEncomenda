package com.zelly.encomendas.encomendasZelly.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "usuarios")
@Entity(name = "usuarioEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class usuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long matricula;
    private String Cargo;
    private String username;
    private String password;

    @Embedded
    private enderecoEntity endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<encomendaEntity> encomendas;


}
