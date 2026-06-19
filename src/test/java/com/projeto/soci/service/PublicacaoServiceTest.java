package com.projeto.soci.service;

import com.projeto.soci.dto.entrada.PublicacaoCreateDto;
import com.projeto.soci.dto.saida.PublicacaoDto;
import com.projeto.soci.mapper.PublicacaoMapper;
import com.projeto.soci.model.Publicacao;
import com.projeto.soci.model.Usuario;
import com.projeto.soci.repository.PublicacaoRepository;
import com.projeto.soci.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicacaoServiceTest {

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private PublicacaoService publicacaoService;

    @BeforeEach
    public void setup() {
        SecurityContext ctx = mock(SecurityContext.class);
        Authentication auth = mock(Authentication.class);
        Usuario u = new Usuario();
        u.setId_usuario(1L);
        u.setNome("Teste");
        u.setEmail("t@t.com");
        when(auth.getPrincipal()).thenReturn(u);
        when(ctx.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(ctx);
    }

    @Test
    public void criar_deveSalvarPublicacao() {
        PublicacaoCreateDto dto = new PublicacaoCreateDto("conteudo teste", null);
        Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Publicacao p = PublicacaoMapper.toEntity(dto, user);
        when(publicacaoRepository.save(any(Publicacao.class))).thenReturn(p);
        PublicacaoDto criado = publicacaoService.criar(dto);
        assertNotNull(criado);
        verify(publicacaoRepository, times(1)).save(any(Publicacao.class));
    }

    @Test
    public void deletar_deveRemoverPublicacaoQuandoExiste() {
        Usuario u = new Usuario();
        u.setId_usuario(2L);
        Publicacao p = new Publicacao();
        p.setId_publicacao(10L);
        p.setUsuario(u);
        when(publicacaoRepository.findById(10L)).thenReturn(Optional.of(p));
        publicacaoService.deletar(10L);
        verify(publicacaoRepository, times(1)).delete(p);
    }
}
