package com.zelly.encomendas.encomendasZelly.controller;

import com.zelly.encomendas.encomendasZelly.repository.produtoRepository;
import com.zelly.encomendas.encomendasZelly.service.produto.dadosListagemProdutos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class produtoController {
    @Autowired
    private produtoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<Page<dadosListagemProdutos>> listarProdutos(@PageableDefault(size=10, sort={"id"}) Pageable paginacao){
        var page = produtoRepository.findAll(paginacao).map(dadosListagemProdutos::new);
        return ResponseEntity.ok(page);
    }
}
