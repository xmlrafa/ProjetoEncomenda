package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import com.zelly.encomendas.encomendaszelly.repository.produtoRepository;
import com.zelly.encomendas.encomendaszelly.service.produto.ProdutoService;
import com.zelly.encomendas.encomendaszelly.service.produto.dadosAtualizacaoProduto;
import com.zelly.encomendas.encomendaszelly.service.produto.dadosCadastroProduto;
import com.zelly.encomendas.encomendaszelly.service.produto.dadosListagemProdutos;
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
public class produtoController {
    private produtoRepository produtoRepository;
    private ProdutoService produtoService;
    @Autowired
    public produtoController(produtoRepository produtoRepository, ProdutoService produtoService){
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }


    @GetMapping
    public ResponseEntity<Page<dadosListagemProdutos>> listarProdutos(@PageableDefault(size=10, sort={"id"}) Pageable paginacao){
        var page = produtoRepository.findAll(paginacao).map(dadosListagemProdutos::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarProduto(@RequestBody dadosCadastroProduto dados, Authentication authentication, UriComponentsBuilder uriComponentsBuilder){
        produtoService.cadastrarProduto(dados, authentication, uriComponentsBuilder);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarProduto(@PathVariable Long id, Authentication authentication){
        produtoService.deletarProduto(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<dadosCadastroProduto> atualizarProduto(@RequestBody @Valid dadosAtualizacaoProduto dados, Authentication authentication){
        var produto = produtoService.editarProduto(dados, authentication);
        return ResponseEntity.ok(produto);
    }

}
