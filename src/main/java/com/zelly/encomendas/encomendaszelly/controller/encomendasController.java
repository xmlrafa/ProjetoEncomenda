package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.repository.produtoRepository;
import com.zelly.encomendas.encomendaszelly.service.encomenda.Status;
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

    @Autowired
    private encomendaService encomendaService;


    @GetMapping
    public Page<encomendaEntity> listarEncomendas(Pageable pageable) {
        return encomendaService.listarEncomendasPaginadas(pageable);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<encomendaEntity> buscaPorCliente(@PathVariable Long clienteId){
        return encomendaRepository.findByClienteId(clienteId);
    }
    @GetMapping("/usuario/{usuarioId}")
    public List<encomendaEntity> buscaPorUsuario(@PathVariable Long usuarioId){
        return encomendaRepository.findByUsuarioId(usuarioId);
    }
    @GetMapping("/status/{status}")
    public List<encomendaEntity> buscaPorStatus(@PathVariable Status status){
        return encomendaRepository.findByStatus(status);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<encomendaEntity> cadastrarEncomenda(@RequestBody encomendaEntity encomenda){
        encomendaEntity encomendaSalva = encomendaService.salvarEncomenda(encomenda);
        return new ResponseEntity<>(encomendaSalva, HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarEncomenda(@RequestBody encomendaEntity encomenda){
        encomendaEntity encomendaAtualizada = encomendaService.atualizarEncomenda(encomenda.getId(), encomenda.getStatus(), encomenda.getDataEntrega());

        return ResponseEntity.ok(new encomendaEntity(encomendaAtualizada));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarEncomenda(@PathVariable Long id){
        var encomenda = encomendaRepository.getReferenceById(id);
        encomendaRepository.delete(encomenda);
        return ResponseEntity.noContent().build();
    }
}
