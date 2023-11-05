package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosCadastroEncomenda;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosDetalhamentoEncomenda;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosListagemEncomendas;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarEncomenda(@RequestBody dadosCadastroEncomenda dados, UriComponentsBuilder uriComponentsBuilder){
        var encomenda= new encomendaEntity(dados);
        encomendaRepository.save(encomenda);

        var uri = uriComponentsBuilder.path("/encomenda/{id}").buildAndExpand(encomenda.getId()).toUri();
        return ResponseEntity.created(uri).body(new dadosDetalhamentoEncomenda(encomenda));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarEncomenda(@PathVariable Long id){
        var encomenda = encomendaRepository.getReferenceById(id);
        encomendaRepository.delete(encomenda);
        return ResponseEntity.noContent().build();
    }
}
