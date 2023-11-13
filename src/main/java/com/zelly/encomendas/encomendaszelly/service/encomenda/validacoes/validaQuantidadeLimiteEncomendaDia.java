package com.zelly.encomendas.encomendaszelly.service.encomenda.validacoes;

import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class validaQuantidadeLimiteEncomendaDia implements validadorEncomenda {
    @Autowired
    private encomendaRepository encomendaRepository;


    @Override
    public void validar(encomendaEntity encomenda) {
        LocalDateTime diaPrevisaoEntrega = LocalDateTime.now();
       //var quantidadeEncomendas = encomendaRepository.countEncomendasEmAndamento();
       //long diasPrevisao = quantidadeEncomendas / 10 + 1;
        encomenda.setDataPrevisaoEntrega(diaPrevisaoEntrega.plusDays(1));

        }
    }

