package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.usuarioRepository;
import com.zelly.encomendas.encomendaszelly.service.produto.dadosCadastroProduto;
import com.zelly.encomendas.encomendaszelly.service.usuario.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class usuarioController {
    @Autowired
    private usuarioRepository usuarioRepository;



    @GetMapping
    public ResponseEntity<Page<dadosListagemUsuarios>> listarUsuarios(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao){
        var page= usuarioRepository.findAll(paginacao).map(dadosListagemUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody dadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder){
        var usuario = new usuarioEntity(dados);
        userService.gerarHashSenha(usuario);
        usuarioRepository.save(usuario);

        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new dadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody dadosAtualizacaoUsuario dados){
        var usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizarUsuario(dados);

        return ResponseEntity.ok(new dadosCadastroUsuario(usuario));
    }


}
