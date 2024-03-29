package com.zelly.encomendas.encomendaszelly.service.log;

import com.zelly.encomendas.encomendaszelly.model.LogEntity;
import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    // TODO: 18/11/2023 alterar para que ele mostre: a entidade, o usuario, o tipo de ação (crud) 
    public void salvarLog(String acao, String entidade, UsuarioEntity usuario){
        LogEntity log = new LogEntity();
        log.setAcao(acao);
        log.setEntidade(entidade);
        log.setUsuario(usuario);
        log.setDataAcao(LocalDateTime.now());
        logRepository.save(log);
    }
}
