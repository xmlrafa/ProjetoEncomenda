package com.zelly.encomendas.encomendaszelly.service.usuario;

import com.zelly.encomendas.encomendaszelly.model.usuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.usuarioRepository;
import com.zelly.encomendas.encomendaszelly.service.log.LogService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final usuarioRepository usuarioRepository;
    private final LogService logService;
    @Autowired
    public UsuarioService(usuarioRepository usuarioRepository, LogService logService) {
        this.usuarioRepository = usuarioRepository;
        this.logService = logService;
    }


    public static void gerarHashSenha(usuarioEntity usuario) {
        String hashSenha = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(12));
        usuario.setPassword(hashSenha);
    }
    public dadosDetalhamentoUsuario cadastrarUsuario(dadosCadastroUsuario dados, Authentication authentication) {
        Long userId = ((usuarioEntity)authentication.getPrincipal()).getId();
        var usuario = new usuarioEntity(dados);
        UsuarioService.gerarHashSenha(usuario);
        usuarioRepository.save(usuario);

        String nomeEntidade = this.getClass().getSimpleName();
        usuarioEntity usuarioDaAcao = usuarioRepository.getReferenceById(userId);

        logService.salvarLog("Usuario cadastrado: "+ usuario.getUsername(), nomeEntidade, usuarioDaAcao);
        return new dadosDetalhamentoUsuario(usuario);
    }
    public void excluirUsuario(Long id, Authentication authentication) {
        Long userId = ((usuarioEntity)authentication.getPrincipal()).getId();
        usuarioEntity usuario = usuarioRepository.getReferenceById(id);
        String nomeEntidade = this.getClass().getSimpleName();
        usuarioEntity usuarioDaAcao = usuarioRepository.getReferenceById(userId);
        logService.salvarLog("Usuario excluido: "+ usuario.getUsername(), nomeEntidade, usuarioDaAcao);

        usuarioRepository.delete(usuario);
    }

    public dadosDetalhamentoUsuario atualizarUsuario(dadosAtualizacaoUsuario dados, Authentication authentication) {
        Long userId = ((usuarioEntity)authentication.getPrincipal()).getId();
        usuarioEntity usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizarUsuario(dados);
        String nomeEntidade = this.getClass().getSimpleName();
        usuarioEntity usuarioDaAcao = usuarioRepository.getReferenceById(userId);
        logService.salvarLog("Usuario atualizado: "+ usuario.getUsername(), nomeEntidade, usuarioDaAcao);
        usuarioRepository.save(usuario);
        return new dadosDetalhamentoUsuario(usuario);
    }




    public List<usuarioEntity> listarTodos() {
                return usuarioRepository.findAll();
    }
}
