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

    public void excluirUsuario(Long id) {
        usuarioEntity usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }

    public usuarioEntity atualizarUsuario(dadosAtualizacaoUsuario dados) {
        usuarioEntity usuario = usuarioRepository.getReferenceById(dados.id());
        return usuarioRepository.save(usuario);
    }

    public dadosDetalhamentoUsuario cadastrarUsuario(dadosCadastroUsuario dados, Authentication authentication) {
        Long userId = ((usuarioEntity)authentication.getPrincipal()).getId();
        var usuario = new usuarioEntity(dados);
        UsuarioService.gerarHashSenha(usuario);
        usuarioRepository.save(usuario);
        logService.salvarLog("teste salvar log");
        return new dadosDetalhamentoUsuario(usuario);
    }


    public List<usuarioEntity> listarTodos() {
                return usuarioRepository.findAll();
    }
}
