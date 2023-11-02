package com.zelly.encomendas.encomendasZelly.model;

import jakarta.persistence.*;
import lombok.*;

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
    private com.zelly.encomendas.encomendasZelly.model.enderecoEntity enderecoEntity;


}
