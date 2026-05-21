package com.projeto.soci.service;

import com.projeto.soci.dto.DadosCadastroUsuario;
import com.projeto.soci.dto.DadosCadastroUsuarioSaida;
import com.projeto.soci.model.Usuario;
import com.projeto.soci.repository.UsuarioRepository;
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


    @Transactional
    public DadosCadastroUsuarioSaida cadastrarUsuario(DadosCadastroUsuario dadosCadastroUsuario) {

        var usuario = new Usuario(dadosCadastroUsuario);

        usuarioRepository.save(usuario);

        return new DadosCadastroUsuarioSaida(usuario);

    }

    public List<DadosCadastroUsuarioSaida> listarUsuarios() {
        return usuarioRepository.findAll()
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
        // update fields
        u.setNome(dados.nome());
        u.setEmail(dados.email());
        if (dados.dataNascimento() != null) {
            u.setData_nascimento(java.util.Date.from(dados.dataNascimento().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
        }
        if (dados.senha() != null && !dados.senha().isBlank()) u.setSenha(dados.senha());
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
        usuarioRepository.delete(opt.get());
        return true;
    }
}
