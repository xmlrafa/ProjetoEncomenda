package com.zelly.encomendas.encomendaszelly.service.encomenda;

import com.zelly.encomendas.encomendaszelly.model.ClienteEntity;
import com.zelly.encomendas.encomendaszelly.model.EncomendaEntity;
import com.zelly.encomendas.encomendaszelly.model.ProdutoEntity;
import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.clienteRepository;
import com.zelly.encomendas.encomendaszelly.repository.encomendaRepository;
import com.zelly.encomendas.encomendaszelly.repository.ProdutoRepository;
import com.zelly.encomendas.encomendaszelly.repository.UsuarioRepository;
import com.zelly.encomendas.encomendaszelly.service.encomenda.validacoes.validadorEncomenda;
import com.zelly.encomendas.encomendaszelly.service.log.LogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EncomendaService {
    private final encomendaRepository encomendaRepository;
    private final ProdutoRepository produtoRepository;
    private final clienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final List<validadorEncomenda> validadores;
    private final LogService logService;
    @Autowired
    public EncomendaService(encomendaRepository encomendaRepository, ProdutoRepository produtoRepository, clienteRepository clienteRepository, UsuarioRepository usuarioRepository, List<validadorEncomenda> validadorEncomendas, LogService logService){
    this.produtoRepository = produtoRepository;
    this.usuarioRepository = usuarioRepository;
    this.validadores = validadorEncomendas;
    this.encomendaRepository = encomendaRepository;
    this.clienteRepository = clienteRepository;
    this.logService = logService;
    }

    public void deletarEncomenda(Long id, Authentication authentication) {
        var encomenda = encomendaRepository.getReferenceById(id);

        Long userId = ((UsuarioEntity)authentication.getPrincipal()).getId();
        String nomeEntidade = this.getClass().getSimpleName();
        UsuarioEntity usuarioDaAcao = usuarioRepository.getReferenceById(userId);

        logService.salvarLog("A encomenda foi deletada de id: "+ encomenda.getId(), nomeEntidade, usuarioDaAcao);

        encomendaRepository.delete(encomenda);
    }


    public EncomendaEntity salvarEncomenda(EncomendaEntity encomenda, Long userId){
        ClienteEntity cliente = clienteRepository.findById(encomenda.getCliente().getId())
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
        String nomeEntidade = this.getClass().getSimpleName();
        UsuarioEntity usuarioDaAcao = usuarioRepository.getReferenceById(userId);

        logService.salvarLog("Foi realizada uma nova encomenda de id: "+ encomenda.getId(), nomeEntidade, usuarioDaAcao);
        return encomendaRepository.save(encomenda);
    }

    public EncomendaEntity atualizarEncomenda(long encomendaId, Status novoStatus, LocalDateTime dataEntrega, Authentication authentication) {
        EncomendaEntity encomenda = encomendaRepository.getReferenceById(encomendaId);

        if(novoStatus != null){
            encomenda.setStatus(novoStatus);
        }
        if(dataEntrega != null){
            encomenda.setDataEntrega(dataEntrega);
        }

        ClienteEntity cliente = clienteRepository.findById(encomenda.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID" + encomenda.getCliente().getId()));

        List<ProdutoEntity> produtos = new ArrayList<>();
        for (ProdutoEntity produto:encomenda.getProdutos()){
            ProdutoEntity produtoExistente = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: "+ produto.getId()));
            produtos.add(produtoExistente);
        }

        encomenda.setCliente(cliente);
        encomenda.setProdutos(produtos);

        Long userId = ((UsuarioEntity)authentication.getPrincipal()).getId();
        String nomeEntidade = this.getClass().getSimpleName();
        UsuarioEntity usuarioDaAcao = usuarioRepository.getReferenceById(userId);

        logService.salvarLog("A encomenda foi atualizada de id: "+ encomenda.getId(), nomeEntidade, usuarioDaAcao);
        return encomendaRepository.save(encomenda);

    }

    public Page<EncomendaEntity> listarEncomendasPaginadas(Pageable pageable) {
        return encomendaRepository.findAll(pageable);
    }
}
