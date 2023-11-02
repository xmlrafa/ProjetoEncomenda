package com.zelly.encomendas.encomendasZelly.repository;

import com.zelly.encomendas.encomendasZelly.model.produtoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface produtoRepository extends JpaRepository<produtoEntity, Long> {
}
