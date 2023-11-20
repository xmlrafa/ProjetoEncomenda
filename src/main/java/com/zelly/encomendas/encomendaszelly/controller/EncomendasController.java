package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.exception.ValidacaoException;
import com.zelly.encomendas.encomendaszelly.model.ClienteEntity;
import com.zelly.encomendas.encomendaszelly.model.EncomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.service.encomenda.Status;
import com.zelly.encomendas.encomendaszelly.service.encomenda.EncomendaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encomenda")
public class EncomendasController {
    private encomendaRepository encomendaRepository;
    private clienteRepository clienteRepository;
    private EncomendaService encomendaService;
    @Autowired
    public EncomendasController(encomendaRepository encomendaRepository, clienteRepository clienteRepository, EncomendaService encomendaService){
        this.encomendaRepository = encomendaRepository;
        this.clienteRepository = clienteRepository;
        this.encomendaService = encomendaService;
    }


    @GetMapping
    public Page<EncomendaEntity> listarEncomendas(Pageable pageable) {
        return encomendaService.listarEncomendasPaginadas(pageable);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<EncomendaEntity> buscaPorCliente(@PathVariable Long clienteId){
        ClienteEntity cliente = clienteRepository.findById(clienteId).orElseThrow(()-> new ValidacaoException("Cliente não encontrado com o id: "+clienteId));
        return encomendaRepository.findByCliente(cliente);
    }
    @GetMapping("/usuario/{usuarioId}")
    public List<EncomendaEntity> buscaPorUsuario(@PathVariable Long usuarioId){
        return encomendaRepository.findByUsuarioId(usuarioId);
    }
    @GetMapping("/status/{status}")
    public List<EncomendaEntity> buscaPorStatus(@PathVariable Status status){
        return encomendaRepository.findByStatus(status);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<EncomendaEntity> cadastrarEncomenda(@RequestBody EncomendaEntity encomenda, Authentication authentication){
        Long userId = ((UsuarioEntity)authentication.getPrincipal()).getId();
        EncomendaEntity encomendaSalva = encomendaService.salvarEncomenda(encomenda, userId);
        return new ResponseEntity<>(encomendaSalva, HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<EncomendaEntity> atualizarEncomenda(@RequestBody EncomendaEntity encomenda, Authentication authentication){
        EncomendaEntity encomendaAtualizada = encomendaService.atualizarEncomenda(encomenda.getId(), encomenda.getStatus(), encomenda.getDataEntrega(), authentication);

        return ResponseEntity.ok(new EncomendaEntity(encomendaAtualizada));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<EncomendaEntity> deletarEncomenda(@PathVariable Long id, Authentication authentication){
        encomendaService.deletarEncomenda(id, authentication);
        return ResponseEntity.noContent().build();
    }
}
