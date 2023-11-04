package com.zelly.encomendas.encomendasZelly.model;

import com.zelly.encomendas.encomendasZelly.service.usuario.dadosCadastroUsuario;
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
    private String cargo;
    private String username;
    private String password;

    @Embedded
    private enderecoEntity endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<encomendaEntity> encomendas;


    public usuarioEntity(dadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.matricula = dados.matricula();
        this.cargo = dados.cargo();
        this.username = dados.username();
        this.password = dados.password();
        this.endereco = dados.endereco();
    }
}
