package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.service.cliente.dadosAtualizacaoCliente;
import com.zelly.encomendas.encomendaszelly.service.cliente.dadosCadastroCliente;
import com.zelly.encomendas.encomendaszelly.service.cliente.dadosListagemClientes;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    // TODO: 04/11/2023 EU fiquei com algumas dúvidas aqui, não sei o que o uricomponentsbuilder faz exatamente 
    // TODO: 04/11/2023 esqueci o que o valid faz 
    // TODO: 04/11/2023 Eu acho que criei a entidade ou o save errado, pq dessa forma ja estou salvando o cliente e a encomenda 
    // TODO: 04/11/2023 sera q funciona? 
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid dadosCadastroCliente dados, UriComponentsBuilder uriBuilder){
        var cliente = new clienteEntity(dados);
        clienteRepository.save(cliente);

        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new dadosCadastroCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirClienteFisico(@PathVariable Long id){
        var cliente = clienteRepository.getReferenceById(id);
        clienteRepository.delete(cliente);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarCliente(@RequestBody @Valid dadosAtualizacaoCliente dados){
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new dadosCadastroCliente(cliente));
    }

}
