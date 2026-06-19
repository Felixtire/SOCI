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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private ConexaoRepository conexaoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private CurtidasRepository curtidasRepository;

    @Autowired
    private EventoRepository eventoRepository;

    private final EncoderService encoderService;

    public CadastroService(EncoderService encoderService){
        this.encoderService = encoderService;
    }


    @Transactional
    public DadosCadastroUsuarioSaida cadastrarUsuario(DadosCadastroUsuario dadosCadastroUsuario) {

        var usuario = new Usuario(dadosCadastroUsuario);

        usuario.setSenha(encoderService.encode(usuario.getSenha()));

        usuarioRepository.save(usuario);

        return new DadosCadastroUsuarioSaida(usuario);

    }

    public List<DadosCadastroUsuarioSaida> listarUsuarios(Long idUsuarioLogado) {
        return usuarioRepository.listarTodosMenosUm(idUsuarioLogado)
                .stream()
                .map(DadosCadastroUsuarioSaida::new)
                .collect(Collectors.toList());
    }

    public DadosCadastroUsuarioSaida buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(DadosCadastroUsuarioSaida::new)
                .orElse(null);
    }

    @Transactional
    public DadosCadastroUsuarioSaida atualizarUsuario(Long id, DadosCadastroUsuario dados) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (opt.isEmpty()) return null;
        Usuario u = opt.get();
        u.setNome(dados.nome());
        u.setEmail(dados.email());
        if (dados.dataNascimento() != null) {
            u.setData_nascimento(java.util.Date.from(dados.dataNascimento().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
        }
        if (dados.senha() != null && !dados.senha().isBlank()){
            u.setSenha(encoderService.encode(dados.senha()));

        }
        u.setCurso(dados.curso());
        u.setFoto_perfil(dados.fotoPerfil());
        u.setBiografia(dados.biografia());
        u.setTipo_usuario(dados.tipoUsuario());
        u.setRgm(dados.rgm());

        usuarioRepository.save(u);
        return new DadosCadastroUsuarioSaida(u);
    }

    @Transactional
    public boolean deletarUsuario(Long id) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (opt.isEmpty()) return false;

        // deletar em ordem para não violar constraints (caso o schema atual não tenha ON DELETE CASCADE)
        // 1. conexoes onde participa
        conexaoRepository.deleteByUsuarioId(id);
        // 2. curtidas e comentarios relacionados diretamente e por publicacoes
        curtidasRepository.deleteByUsuarioOrPublicacaoUsuario(id);
        comentarioRepository.deleteByUsuarioOrPublicacaoUsuario(id);
        // 3. publicacoes do usuario
        publicacaoRepository.deleteByUsuarioId(id);
        // 4. eventos do usuario
        eventoRepository.deleteByUsuarioId(id);

        // 5. por fim, deletar o usuario
        usuarioRepository.delete(opt.get());
        return true;
    }

    @Transactional
    public void deletarUsuarios() {
        usuarioRepository.deleteAll();
    }
}
