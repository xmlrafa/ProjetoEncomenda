package com.zelly.encomendas.encomendaszelly.repository;

import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface encomendaRepository extends JpaRepository<encomendaEntity, Long> {
}
