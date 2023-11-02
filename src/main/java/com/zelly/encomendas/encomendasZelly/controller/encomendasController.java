package com.zelly.encomendas.encomendasZelly.controller;

import com.zelly.encomendas.encomendasZelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendasZelly.service.encomenda.dadosListagemEncomendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encomenda")
public class encomendasController {
    @Autowired
    private encomendaRepository encomendaRepository;

    @GetMapping
    public ResponseEntity<Page<dadosListagemEncomendas>> listarEncomendas(@PageableDefault(size=10, sort={"id"})Pageable paginacao){
        var page = encomendaRepository.findAll(paginacao).map(dadosListagemEncomendas::new);
        return ResponseEntity.ok(page);
    }
}
