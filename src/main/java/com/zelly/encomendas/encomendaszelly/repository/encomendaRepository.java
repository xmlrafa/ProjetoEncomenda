package com.zelly.encomendas.encomendaszelly.repository;

import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;

public interface encomendaRepository extends JpaRepository<encomendaEntity, Long> {
   @Query("SELECT COUNT(e) FROM encomendaEntity e WHERE e.status NOT IN ('PRONTA', 'ENTREGUE')")
   int countEncomendasEmAndamento();
   }
