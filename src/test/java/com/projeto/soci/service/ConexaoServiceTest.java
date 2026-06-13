package com.projeto.soci.service;

import com.projeto.soci.model.Conexao;
import com.projeto.soci.model.Usuario;
import com.projeto.soci.repository.ConexaoRepository;
import com.projeto.soci.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConexaoServiceTest {

    @Mock
    private ConexaoRepository conexaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ConexaoService conexaoService;

    @Test
    public void conectarUsuarios_deveSalvarConexao() {
        Usuario origem = new Usuario();
        origem.setId_usuario(1L);
        Usuario destino = new Usuario();
        destino.setId_usuario(2L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(origem));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(destino));
        when(conexaoRepository.ligarConexoesPorUsuario(1L)).thenReturn(List.of());
        Conexao c = new Conexao();
        c.setId_conexao(5L);
        when(conexaoRepository.save(any(Conexao.class))).thenReturn(c);
        conexaoService.conectarUsuarios(1L, 2L);
        verify(conexaoRepository, times(1)).save(any(Conexao.class));
    }

    @Test
    public void excluirConexoesPorUsuario_deveDeletarQuandoExiste() {
        Usuario origem = new Usuario();
        origem.setId_usuario(1L);
        Usuario destino = new Usuario();
        destino.setId_usuario(2L);
        Conexao c = new Conexao();
        c.setId_conexao(7L);
        c.setUsuarioOrigem(origem);
        c.setUsuarioDestino(destino);
        when(conexaoRepository.findById(7L)).thenReturn(Optional.of(c));
        conexaoService.excluirConexoesPorUsuario(7L);
        verify(conexaoRepository, times(1)).deleteById(7L);
    }
}
