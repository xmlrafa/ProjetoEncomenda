package com.zelly.encomendas.encomendasZelly.controller;

import com.zelly.encomendas.encomendasZelly.model.produtoEntity;
import com.zelly.encomendas.encomendasZelly.repository.produtoRepository;
import com.zelly.encomendas.encomendasZelly.service.produto.dadosCadastroProduto;
import com.zelly.encomendas.encomendasZelly.service.produto.dadosListagemProdutos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarProduto(@RequestBody dadosCadastroProduto dados, UriComponentsBuilder uriComponentsBuilder){
        var produto = new produtoEntity(dados);
        produtoRepository.save(produto);

        var uri = uriComponentsBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new dadosCadastroProduto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarProduto(@PathVariable Long id){
        var produto = produtoRepository.getReferenceById(id);
        produtoRepository.delete(produto);

        return ResponseEntity.noContent().build();
    }

}