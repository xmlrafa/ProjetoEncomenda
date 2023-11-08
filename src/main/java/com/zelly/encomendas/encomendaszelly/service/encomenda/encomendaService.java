package com.zelly.encomendas.encomendaszelly.service.encomenda;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.repository.produtoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class encomendaService {
    @Autowired
    private encomendaRepository encomendaRepository;
    @Autowired
    private produtoRepository produtoRepository;
    @Autowired
    private clienteRepository clienteRepository;

    public encomendaEntity salvarEncomenda(encomendaEntity encomenda){
        clienteEntity cliente = clienteRepository.findById(encomenda.getClienteEntity().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID" + encomenda.getClienteEntity().getId()));

        List<produtoEntity> produtos = new ArrayList<>();
        for (produtoEntity produto:encomenda.getProdutos()){
            produtoEntity produtoExistente = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: "+ produto.getId()));
            produtos.add(produtoExistente);
        }
        encomenda.setClienteEntity(cliente);
        encomenda.setProdutos(produtos);

        return encomendaRepository.save(encomenda);
    }
}
