package com.zelly.encomendas.encomendaszelly.service.produto;

import com.zelly.encomendas.encomendaszelly.model.ProdutoEntity;
import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.ProdutoRepository;
import com.zelly.encomendas.encomendaszelly.repository.usuarioRepository;
import com.zelly.encomendas.encomendaszelly.service.log.LogService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final LogService logService;
    private final usuarioRepository usuarioRepository;

    public ProdutoService(ProdutoRepository produtoRepository, LogService logService, usuarioRepository usuarioRepository){
        this.produtoRepository = produtoRepository;
        this.logService = logService;
        this.usuarioRepository = usuarioRepository;
    }
    public void cadastrarProduto(DadosCadastroProduto dados, Authentication authentication, UriComponentsBuilder uriComponentsBuilder) {
        var produto = new ProdutoEntity(dados);
        produtoRepository.save(produto);
        uriComponentsBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
        String nomeEntidade = this.getClass().getSimpleName();
        Long userId = ((UsuarioEntity)authentication.getPrincipal()).getId();
        UsuarioEntity usuario = usuarioRepository.getReferenceById(userId);
        logService.salvarLog("Produto cadastrado: "+produto.getNome(), nomeEntidade, usuario);
    }

    public DadosCadastroProduto editarProduto(@Valid DadosAtualizacaoProduto dados, Authentication authentication) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);

        String nomeEntidade = this.getClass().getSimpleName();
        Long userId = ((UsuarioEntity)authentication.getPrincipal()).getId();
        UsuarioEntity usuario = usuarioRepository.getReferenceById(userId);
        logService.salvarLog("Produto editado: "+produto.getNome(), nomeEntidade, usuario);
        return new DadosCadastroProduto(produto);
    }

    public void deletarProduto(Long id, Authentication authentication) {
        var produto = produtoRepository.getReferenceById(id);
        String nomeEntidade = this.getClass().getSimpleName();
        Long userId = ((UsuarioEntity)authentication.getPrincipal()).getId();
        UsuarioEntity usuario = usuarioRepository.getReferenceById(userId);
        logService.salvarLog("Produto cadastrado: "+produto.getNome(), nomeEntidade, usuario);
        produtoRepository.delete(produto);
    }
}
