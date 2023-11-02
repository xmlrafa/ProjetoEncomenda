package com.zelly.encomendas.encomendasZelly.repository;

import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepository extends JpaRepository<usuarioEntity, Long> {
}
