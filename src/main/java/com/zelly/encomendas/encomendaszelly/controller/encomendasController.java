package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.repository.produtoRepository;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosListagemEncomendas;
import com.zelly.encomendas.encomendaszelly.service.encomenda.encomendaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encomenda")
public class encomendasController {
    @Autowired
    private encomendaRepository encomendaRepository;
    @Autowired
    private clienteRepository clienteRepository;
    @Autowired
    private produtoRepository produtoRepository;

    @Autowired
    private encomendaService encomendaService;


    @GetMapping
    public ResponseEntity<Page<dadosListagemEncomendas>> listarEncomendas(@PageableDefault(size=10, sort={"id"})Pageable paginacao){
        var page = encomendaRepository.findAll(paginacao).map(dadosListagemEncomendas::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<encomendaEntity> cadastrarEncomenda(@RequestBody encomendaEntity encomenda){
        encomendaEntity encomendaSalva = encomendaService.salvarEncomenda(encomenda);
        return new ResponseEntity<>(encomendaSalva, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarEncomenda(@PathVariable Long id){
        var encomenda = encomendaRepository.getReferenceById(id);
        encomendaRepository.delete(encomenda);
        return ResponseEntity.noContent().build();
    }
}
