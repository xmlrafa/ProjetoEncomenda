package com.zelly.encomendas.encomendaszelly.repository;

import com.zelly.encomendas.encomendaszelly.model.ClienteEntity;
import com.zelly.encomendas.encomendaszelly.model.EncomendaEntity;
import com.zelly.encomendas.encomendaszelly.service.encomenda.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface encomendaRepository extends JpaRepository<EncomendaEntity, Long> {
   @Query("SELECT COUNT(e) FROM encomendaEntity e WHERE e.status NOT IN ('PRONTA', 'ENTREGUE')")
   int countEncomendasEmAndamento();

   List<EncomendaEntity> findByCliente(ClienteEntity cliente);
   List<EncomendaEntity> findByUsuarioId(Long usuarioId);
   List<EncomendaEntity> findByStatus(Status status);
   }
