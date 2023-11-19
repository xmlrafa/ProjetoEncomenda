package com.zelly.encomendas.encomendaszelly.service.produto;

import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.produtoRepository;
import com.zelly.encomendas.encomendaszelly.repository.usuarioRepository;
import com.zelly.encomendas.encomendaszelly.service.log.LogService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProdutoService {
    private produtoRepository produtoRepository;
    private LogService logService;
    private usuarioRepository usuarioRepository;

    public ProdutoService(produtoRepository produtoRepository, LogService logService, usuarioRepository usuarioRepository){
        this.produtoRepository = produtoRepository;
        this.logService = logService;
        this.usuarioRepository = usuarioRepository;
    }
    public void cadastrarProduto(dadosCadastroProduto dados, Authentication authentication, UriComponentsBuilder uriComponentsBuilder) {
        var produto = new produtoEntity(dados);
        produtoRepository.save(produto);
        uriComponentsBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
        String nomeEntidade = this.getClass().getSimpleName();
        Long userId = ((usuarioEntity)authentication.getPrincipal()).getId();
        usuarioEntity usuario = usuarioRepository.getReferenceById(userId);
        logService.salvarLog("Produto cadastrado: "+produto.getNome(), nomeEntidade, usuario);
    }

    public dadosCadastroProduto editarProduto(@Valid dadosAtualizacaoProduto dados, Authentication authentication) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);

        String nomeEntidade = this.getClass().getSimpleName();
        Long userId = ((usuarioEntity)authentication.getPrincipal()).getId();
        usuarioEntity usuario = usuarioRepository.getReferenceById(userId);
        logService.salvarLog("Produto editado: "+produto.getNome(), nomeEntidade, usuario);
        return new dadosCadastroProduto(produto);
    }

    public void deletarProduto(Long id, Authentication authentication) {
        var produto = produtoRepository.getReferenceById(id);
        String nomeEntidade = this.getClass().getSimpleName();
        Long userId = ((usuarioEntity)authentication.getPrincipal()).getId();
        usuarioEntity usuario = usuarioRepository.getReferenceById(userId);
        logService.salvarLog("Produto cadastrado: "+produto.getNome(), nomeEntidade, usuario);
        produtoRepository.delete(produto);
    }
}
