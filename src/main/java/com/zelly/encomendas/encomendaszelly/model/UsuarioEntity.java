package com.zelly.encomendas.encomendaszelly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zelly.encomendas.encomendaszelly.service.usuario.DadosAtualizacaoUsuario;
import com.zelly.encomendas.encomendaszelly.service.usuario.dadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "usuarioEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long matricula;
    private String cargo;
    @Column(unique = true)
    @JsonIgnore
    private String login;

    @JsonIgnore
    private String password;

    @Embedded
    @JsonIgnore
    private transient EnderecoEntity endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private transient List<EncomendaEntity> encomendas;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    @JsonIgnore
    public String getPassword(){
        return password;
    }
    @Override
    @JsonIgnore
    public String getUsername() {
        return login;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


    public UsuarioEntity(dadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.matricula = dados.matricula();
        this.cargo = dados.cargo();
        this.login = dados.login();
        this.password = dados.password();
        this.endereco = dados.endereco();
    }
    public void atualizarUsuario(DadosAtualizacaoUsuario dados){
        if (dados.id() != null){
            this.id = dados.id();
        }
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.cargo() != null){
            this.cargo = dados.cargo();
        }
        if(dados.login() != null){
            this.login = dados.login();
        }
        if(dados.password() != null){
            this.password = dados.password();
        }
        if(dados.endereco() != null){
            this.endereco = dados.endereco();
        }
    }
}
