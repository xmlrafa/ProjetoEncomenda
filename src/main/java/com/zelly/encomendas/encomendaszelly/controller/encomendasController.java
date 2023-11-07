package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.repository.produtoRepository;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosCadastroEncomenda;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosDetalhamentoEncomenda;
import com.zelly.encomendas.encomendaszelly.service.encomenda.dadosListagemEncomendas;
import com.zelly.encomendas.encomendaszelly.service.produto.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/encomenda")
public class encomendasController {
    @Autowired
    private encomendaRepository encomendaRepository;
    @Autowired
    private clienteRepository clienteRepository;
    @Autowired
    private produtoRepository produtoRepository;


    @GetMapping
    public ResponseEntity<Page<dadosListagemEncomendas>> listarEncomendas(@PageableDefault(size=10, sort={"id"})Pageable paginacao){
        var page = encomendaRepository.findAll(paginacao).map(dadosListagemEncomendas::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public encomendaEntity cadastrarEncomenda(@RequestBody encomendaEntity encomenda){
        // TODO: 06/11/2023 Estava com erro pra buscar os dados de Produtos e CLientes, então trouxe o entity pra fazer funcionar pra depois tirar essas regras daqui. 
        clienteEntity cliente = clienteRepository.findById(encomenda.getClienteEntity().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID" + encomenda.getClienteEntity().getId()));

        List<produtoEntity> produtos = new ArrayList<>();
        for (produtoEntity produto:encomenda.getProdutos()){
            produtoEntity produtoExistente = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: "+ produto.getId()));
            produtos.add(produtoExistente);
        }
        encomenda.setClienteEntity(cliente);
        encomenda.setProdutos(produtos);
        return encomendaRepository.save(encomenda);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarEncomenda(@PathVariable Long id){
        var encomenda = encomendaRepository.getReferenceById(id);
        encomendaRepository.delete(encomenda);
        return ResponseEntity.noContent().build();
    }
}
