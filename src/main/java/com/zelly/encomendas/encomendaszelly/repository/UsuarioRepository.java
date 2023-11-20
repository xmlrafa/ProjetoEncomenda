package com.zelly.encomendas.encomendaszelly.repository;

import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UserDetails findByLogin(String login);
}
