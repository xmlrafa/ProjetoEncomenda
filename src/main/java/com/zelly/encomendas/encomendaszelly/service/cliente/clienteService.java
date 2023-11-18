package com.zelly.encomendas.encomendaszelly.service.cliente;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class clienteService {
    @Autowired
    private clienteRepository clienteRepository;
    @Autowired
    private LogService logService;
    public void salvar(dadosCadastroCliente dados) {
        var cliente = new clienteEntity(dados);
        clienteRepository.save(cliente);
        logService.salvarLog("Um cliente ");

    }
}
