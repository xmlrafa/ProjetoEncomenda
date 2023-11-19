package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.usuarioRepository;
import com.zelly.encomendas.encomendaszelly.service.usuario.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class usuarioController {
    @Autowired
    private usuarioRepository usuarioRepository;

    private UsuarioService usuarioService;
    @Autowired
    public usuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<usuarioEntity>> listarUsuarios(){
        List<usuarioEntity> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<dadosDetalhamentoUsuario> cadastrarUsuario(@RequestBody dadosCadastroUsuario dados, Authentication authentication /**UriComponentsBuilder uriComponentsBuilder**/){
        // TODO: 18/11/2023 adicionar aqui o retorno do authentication para salvar no log 

        var usuario = usuarioService.cadastrarUsuario(dados, authentication);

//        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
//        return ResponseEntity.created(uri).body(new dadosDetalhamentoUsuario(usuario));
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id){
        // TODO: 18/11/2023 adicionar aqui o retorno do authentication para salvar no log 
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody dadosAtualizacaoUsuario dados){
        // TODO: 18/11/2023 adicionar aqui o retorno do authentication para salvar no log 
        var usuario = usuarioService.atualizarUsuario(dados); //usuarioRepository.getReferenceById(dados.id());

        return ResponseEntity.ok(new dadosCadastroUsuario(usuario));
    }


}
