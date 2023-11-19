package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.usuarioRepository;
import com.zelly.encomendas.encomendaszelly.service.usuario.UsuarioService;
import com.zelly.encomendas.encomendaszelly.service.usuario.dadosAtualizacaoUsuario;
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
public class usuarioController {
    @Autowired
    private usuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    @Autowired
    public usuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<usuarioEntity>> listarUsuarios() {
        List<usuarioEntity> usuarios = usuarioService.listarTodos();
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
    public ResponseEntity excluirUsuario(@PathVariable Long id, Authentication authentication) {
        usuarioService.excluirUsuario(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<dadosDetalhamentoUsuario> atualizarUsuario(@RequestBody dadosAtualizacaoUsuario dados, Authentication authentication) {
        var usuario = usuarioService.atualizarUsuario(dados, authentication); //usuarioRepository.getReferenceById(dados.id());
        return ResponseEntity.ok(usuario);
    }
}
