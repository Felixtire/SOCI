package com.projeto.soci.service;

import com.projeto.soci.dto.ConexaoResponseDto;
import com.projeto.soci.enuns.StatusConexao;
import com.projeto.soci.model.Conexao;
import com.projeto.soci.repository.ConexaoRepository;
import com.projeto.soci.repository.UsuarioRepository;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConexaoService {

    @Autowired
    private ConexaoRepository conexaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Conexao conectarUsuarios(Long origemId, Long destinoId) {

        var usuarioOrigem = usuarioRepository.findById(origemId).orElseThrow(() -> new RuntimeException("Usuário de origem não encontrado"));
        var usuarioDestino = usuarioRepository.findById(destinoId).orElseThrow(() -> new RuntimeException("Usuário de destino não encontrado"));

        var conexao = new Conexao();

        conexao.setUsuarioOrigem(usuarioOrigem);

        conexao.setUsuarioDestino(usuarioDestino);

        conexao.setStatusConexao(StatusConexao.PENDENTE);


        return conexaoRepository.save(conexao);


    }

    public List<Conexao> listarConexoes() {
        return conexaoRepository.findAll();
    }

    public List<ConexaoResponseDto> listarConexoesPorUsuario(Long usuarioId) {

        return conexaoRepository
                .ligarConexoesPorUsuario(usuarioId)
                .stream().map(ConexaoResponseDto::new).toList();
    }
}
