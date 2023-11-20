package com.zelly.encomendas.encomendaszelly.repository;

import com.zelly.encomendas.encomendaszelly.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface clienteRepository extends JpaRepository<ClienteEntity, Long> {
}
