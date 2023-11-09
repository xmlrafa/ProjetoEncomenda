package com.zelly.encomendas.encomendaszelly.model;

import com.zelly.encomendas.encomendaszelly.service.usuario.dadosAtualizacaoUsuario;
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
public class usuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long matricula;
    private String cargo;
    private String login;
    private String password;

    @Embedded
    private enderecoEntity endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<encomendaEntity> encomendas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername() {
        return login;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public usuarioEntity(dadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.matricula = dados.matricula();
        this.cargo = dados.cargo();
        this.login = dados.login();
        this.password = dados.password();
        this.endereco = dados.endereco();
    }
    public void atualizarUsuario(dadosAtualizacaoUsuario dados){
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
