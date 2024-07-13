package com.zelly.encomendas.encomendaszelly.controller;

import com.zelly.encomendas.encomendaszelly.model.UsuarioEntity;
import com.zelly.encomendas.encomendaszelly.repository.UsuarioRepository;
import com.zelly.encomendas.encomendaszelly.security.TokenService;
import com.zelly.encomendas.encomendaszelly.service.usuario.DadosAtualizacaoUsuario;
import com.zelly.encomendas.encomendaszelly.service.usuario.UsuarioService;
import com.zelly.encomendas.encomendaszelly.service.usuario.dadosCadastroUsuario;
import com.zelly.encomendas.encomendaszelly.service.usuario.dadosDetalhamentoUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.anyLong;


import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private TokenService tokenService;

    @Mock
    private Authentication authentication;

    private String token;
    @BeforeEach
    public void setup(){

        MockitoAnnotations.openMocks(this);

    }
    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testListarUsuarios() throws Exception {
        UsuarioEntity usuario1 = new UsuarioEntity();
        UsuarioEntity usuario2 = new UsuarioEntity();
        when(usuarioService.listarTodos()).thenReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuario"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

        verify(usuarioService, times(1)).listarTodos();
    }


    /**
    @Test
    public void testCadastrarUsuario() throws Exception {
        dadosCadastroUsuario dados = new dadosCadastroUsuario();
        dadosDetalhamentoUsuario detalhes = new dadosDetalhamentoUsuario();
        when(usuarioService.cadastrarUsuario(any(dadosCadastroUsuario.class), any(Authentication.class))).thenReturn(detalhes);

        mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"campo\": \"valor\"}") // Substitua pelo JSON apropriado
                        .principal(authentication))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.campo").value("valor")); // Substitua pelo campo apropriado

        verify(usuarioService, times(1)).cadastrarUsuario(any(dadosCadastroUsuario.class), any(Authentication.class));
    }

    @Test
    public void testExcluirUsuario() throws Exception {
        doNothing().when(usuarioService).excluirUsuario(anyLong(), any(Authentication.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuario/1")
                        .principal(authentication))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(usuarioService, times(1)).excluirUsuario(anyLong(), any(Authentication.class));
    }

    @Test
    public void testAtualizarUsuario() throws Exception {
        DadosAtualizacaoUsuario dados = new DadosAtualizacaoUsuario();
        dadosDetalhamentoUsuario detalhes = new dadosDetalhamentoUsuario();
        when(usuarioService.atualizarUsuario(any(DadosAtualizacaoUsuario.class), any(Authentication.class))).thenReturn(detalhes);

        mockMvc.perform(MockMvcRequestBuilders.put("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"campo\": \"valor\"}") // Substitua pelo JSON apropriado
                        .principal(authentication))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.campo").value("valor")); // Substitua pelo campo apropriado

        verify(usuarioService, times(1)).atualizarUsuario(any(DadosAtualizacaoUsuario.class), any(Authentication.class));
    }
    **/
}
