package com.zelly.encomendas.encomendaszelly.service.encomenda;

import com.zelly.encomendas.encomendaszelly.model.clienteEntity;
import com.zelly.encomendas.encomendaszelly.model.encomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.ProdutoEntity;
import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.repository.ProdutoRepository;
import com.zelly.encomendas.encomendaszelly.repository.usuarioRepository;
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
    private ProdutoRepository produtoRepository;
    @Autowired
    private clienteRepository clienteRepository;

    @Autowired
    usuarioRepository usuarioRepository;

    @Autowired
    private List<validadorEncomenda> validadores;

    public encomendaEntity salvarEncomenda(encomendaEntity encomenda, Long userId){
        clienteEntity cliente = clienteRepository.findById(encomenda.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID" + encomenda.getCliente().getId()));

        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("Usuario nao encontrado com o ID" + userId));

        List<ProdutoEntity> produtos = new ArrayList<>();
        for (ProdutoEntity produto:encomenda.getProdutos()){
            ProdutoEntity produtoExistente = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: "+ produto.getId()));
            produtos.add(produtoExistente);
        }
        validadores.forEach(v -> v.validar(encomenda));

        encomenda.setCliente(cliente);
        encomenda.setProdutos(produtos);
        encomenda.setUsuario(usuario);

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

        List<ProdutoEntity> produtos = new ArrayList<>();
        for (ProdutoEntity produto:encomenda.getProdutos()){
            ProdutoEntity produtoExistente = produtoRepository.findById(produto.getId())
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
