package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.ProdutoEntity;
import com.zelly.encomendas.encomendaszelly.repository.ProdutoRepository;
import com.zelly.encomendas.encomendaszelly.service.produto.ProdutoService;
import com.zelly.encomendas.encomendaszelly.service.produto.DadosAtualizacaoProduto;
import com.zelly.encomendas.encomendaszelly.service.produto.DadosCadastroProduto;
import com.zelly.encomendas.encomendaszelly.service.produto.DadosListagemProdutos;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;
    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, ProdutoService produtoService){
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemProdutos>> listarProdutos(@PageableDefault(size=10, sort={"id"}) Pageable paginacao){
        var page = produtoRepository.findAll(paginacao).map(DadosListagemProdutos::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadastroProduto> cadastrarProduto(@RequestBody DadosCadastroProduto dados, Authentication authentication, UriComponentsBuilder uriComponentsBuilder){
        produtoService.cadastrarProduto(dados, authentication, uriComponentsBuilder);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoEntity> deletarProduto(@PathVariable Long id, Authentication authentication){
        produtoService.deletarProduto(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DadosCadastroProduto> atualizarProduto(@RequestBody @Valid DadosAtualizacaoProduto dados, Authentication authentication){
        var produto = produtoService.editarProduto(dados, authentication);
        return ResponseEntity.ok(produto);
    }

}
