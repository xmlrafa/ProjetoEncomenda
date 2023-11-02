package com.zelly.encomendas.encomendasZelly.controller;

import com.zelly.encomendas.encomendasZelly.repository.usuarioRepository;
import com.zelly.encomendas.encomendasZelly.service.usuario.dadosListagemUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
