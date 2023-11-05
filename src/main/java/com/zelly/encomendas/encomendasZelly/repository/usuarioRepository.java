package com.zelly.encomendas.encomendasZelly.repository;

import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface usuarioRepository extends JpaRepository<usuarioEntity, Long> {
    UserDetails findByLogin(String login);
}
