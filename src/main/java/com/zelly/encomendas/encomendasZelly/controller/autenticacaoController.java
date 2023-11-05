package com.zelly.encomendas.encomendasZelly.controller;

import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;
import com.zelly.encomendas.encomendasZelly.security.DadosTokenJWT;
import com.zelly.encomendas.encomendasZelly.security.TokenService;
import com.zelly.encomendas.encomendasZelly.service.usuario.DadosAutenticacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class autenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    // TODO: 04/11/2023 Ver o que UsernamePasswordAuthenticationTOken faz e o manager.authenticate faz
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var authentication = manager.authenticate(authenticationToken);

       var tokenJWT = tokenService.gerarToken((usuarioEntity)authentication.getPrincipal());

       return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
       // return ResponseEntity.ok().build();
    }

}
