package com.zelly.encomendas.encomendasZelly.service.usuario;

import com.zelly.encomendas.encomendasZelly.model.usuarioEntity;
import com.zelly.encomendas.encomendasZelly.repository.usuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    usuarioRepository usuarioRepository;


    public static void gerarHashSenha(usuarioEntity usuario) {
        String hashSenha = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(12));
        usuario.setPassword(hashSenha);
    }
}
