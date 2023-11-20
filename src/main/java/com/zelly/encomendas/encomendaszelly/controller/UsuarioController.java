package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.UsuarioRepository;
import com.zelly.encomendas.encomendaszelly.service.usuario.UsuarioService;
import com.zelly.encomendas.encomendaszelly.service.usuario.DadosAtualizacaoUsuario;
import com.zelly.encomendas.encomendaszelly.service.usuario.dadosCadastroUsuario;
import com.zelly.encomendas.encomendaszelly.service.usuario.dadosDetalhamentoUsuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<dadosDetalhamentoUsuario> cadastrarUsuario(@RequestBody dadosCadastroUsuario dados, Authentication authentication /**UriComponentsBuilder uriComponentsBuilder**/) {
        var usuario = usuarioService.cadastrarUsuario(dados, authentication);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioEntity> excluirUsuario(@PathVariable Long id, Authentication authentication) {
        usuarioService.excluirUsuario(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<dadosDetalhamentoUsuario> atualizarUsuario(@RequestBody DadosAtualizacaoUsuario dados, Authentication authentication) {
        var usuario = usuarioService.atualizarUsuario(dados, authentication);
        return ResponseEntity.ok(usuario);
    }
}
