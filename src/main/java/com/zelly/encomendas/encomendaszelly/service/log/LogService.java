package com.zelly.encomendas.encomendaszelly.service.log;

import com.zelly.encomendas.encomendaszelly.model.LogEntity;
import com.zelly.encomendas.encomendaszelly.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class LogService {
    @Autowired
    private LogRepository logRepository;

    public void salvarLog(String descricao){
        LogEntity log = new LogEntity();
        log.setDescricao(descricao);
        log.setDataAcao(LocalDateTime.now());
        logRepository.save(log);
    }
}
