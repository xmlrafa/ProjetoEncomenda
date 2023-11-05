package com.zelly.encomendas.encomendasZelly.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class TokenService {
    @Value("123456")
    private String secret;

    public String gerarToken(usuarioEntity usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("encomendasZellyAPI")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token!");
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("encomendasZellyAPI")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
