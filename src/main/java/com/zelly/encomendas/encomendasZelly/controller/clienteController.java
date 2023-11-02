package com.zelly.encomendas.encomendasZelly.controller;

import com.zelly.encomendas.encomendasZelly.repository.clienteRepository;
import com.zelly.encomendas.encomendasZelly.service.cliente.dadosListagemClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class clienteController {
    @Autowired
    private clienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<Page<dadosListagemClientes>> listarClientes(@PageableDefault(size=10, sort={"id"}) Pageable paginacao){
        var page = clienteRepository.findAll(paginacao).map(dadosListagemClientes::new);
        return ResponseEntity.ok(page);
    }

}
