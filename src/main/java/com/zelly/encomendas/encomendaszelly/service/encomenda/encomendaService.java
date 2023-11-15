package com.zelly.encomendas.encomendaszelly.service.encomenda;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.repository.produtoRepository;
import com.zelly.encomendas.encomendaszelly.service.encomenda.validacoes.validadorEncomenda;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    private List<validadorEncomenda> validadores;

    public encomendaEntity salvarEncomenda(encomendaEntity encomenda){
        clienteEntity cliente = clienteRepository.findById(encomenda.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID" + encomenda.getCliente().getId()));

        List<produtoEntity> produtos = new ArrayList<>();
        for (produtoEntity produto:encomenda.getProdutos()){
            produtoEntity produtoExistente = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: "+ produto.getId()));
            produtos.add(produtoExistente);
        }
        validadores.forEach(v -> v.validar(encomenda));

        encomenda.setCliente(cliente);
        encomenda.setProdutos(produtos);
        if (produtos.isEmpty()){
            throw new ListaProdutosVazia();
        }
        return encomendaRepository.save(encomenda);
    }

    public encomendaEntity atualizarEncomenda(long encomendaId, Status novoStatus, LocalDateTime dataEntrega) {
        encomendaEntity encomenda = encomendaRepository.getReferenceById(encomendaId);

        if(novoStatus != null){
            encomenda.setStatus(novoStatus);
        }
        if(dataEntrega != null){
            encomenda.setDataEntrega(dataEntrega);
        }

        clienteEntity cliente = clienteRepository.findById(encomenda.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID" + encomenda.getCliente().getId()));

        List<produtoEntity> produtos = new ArrayList<>();
        for (produtoEntity produto:encomenda.getProdutos()){
            produtoEntity produtoExistente = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: "+ produto.getId()));
            produtos.add(produtoExistente);
        }

        encomenda.setCliente(cliente);
        encomenda.setProdutos(produtos);

        return encomendaRepository.save(encomenda);

    }

    public Page<encomendaEntity> listarEncomendasPaginadas(Pageable pageable) {
        return encomendaRepository.findAll(pageable);
    }
}
