package com.zelly.encomendas.encomendasZelly.repository;

import com.zelly.encomendas.encomendasZelly.model.encomendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface encomendaRepository extends JpaRepository<encomendaEntity, Long> {
}
