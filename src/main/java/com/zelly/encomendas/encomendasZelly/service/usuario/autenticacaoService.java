package com.zelly.encomendas.encomendasZelly.service.usuario;

import com.zelly.encomendas.encomendasZelly.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class autenticacaoService implements UserDetailsService {
    @Autowired
    private usuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return repository.findByLogin(username);
    }

}
