package com.projeto.soci.service;

import com.projeto.soci.dto.entrada.DadosCadastroUsuario;
import com.projeto.soci.dto.saida.DadosCadastroUsuarioSaida;
import com.projeto.soci.model.Usuario;
import com.projeto.soci.repository.UsuarioRepository;
import com.projeto.soci.repository.PublicacaoRepository;
import com.projeto.soci.repository.ConexaoRepository;
import com.projeto.soci.repository.ComentarioRepository;
import com.projeto.soci.repository.CurtidasRepository;
import com.projeto.soci.repository.EventoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastroServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Mock
    private ConexaoRepository conexaoRepository;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private CurtidasRepository curtidasRepository;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private EncoderService encoderService;

    @InjectMocks
    private CadastroService cadastroService;

    @Test
    public void cadastrarUsuario_devePersistirERetornarSaida() {
        DadosCadastroUsuario dados = new DadosCadastroUsuario("Nome", "email@test.com", null, "senha", "curso", null, null, null, null);
        Usuario u = new Usuario(dados);
        when(encoderService.encode(anyString())).thenReturn("encoded");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(u);
        DadosCadastroUsuarioSaida out = cadastroService.cadastrarUsuario(dados);
        assertNotNull(out);
        assertEquals(u.getId_usuario(), out.id());
    }
}
