package com.projeto.soci.service;

import com.projeto.soci.dto.saida.ConexaoOriginaria;
import com.projeto.soci.dto.saida.ConexaoResponseDto;
import com.projeto.soci.dto.saida.RespostaLoginDto;
import com.projeto.soci.dto.saida.RespostaNotificacaoAgrupada;
import com.projeto.soci.enuns.StatusConexao;
import com.projeto.soci.model.Conexao;
import com.projeto.soci.repository.ConexaoRepository;
import com.projeto.soci.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConexaoService {

    @Autowired
    private ConexaoRepository conexaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    public Conexao conectarUsuarios(Long origemId, Long destinoId) {

        var usuarioOrigem = usuarioRepository.findById(origemId).orElseThrow(() -> new RuntimeException("Usuário de origem não encontrado"));
        var usuarioDestino = usuarioRepository.findById(destinoId).orElseThrow(() -> new RuntimeException("Usuário de destino não encontrado"));

        if (usuarioDestino == null || usuarioOrigem == null) {
            throw new RuntimeException("Usuário de origem ou destino não encontrado");
        }
        if (usuarioDestino.equals(usuarioOrigem)) {
            throw new RuntimeException("Usuário de origem e destino não podem ser o mesmo");
        }

        if (conexaoAceitaExiste(origemId, destinoId)) {
            throw new RuntimeException("Conexão já existe entre os usuários");
        }


        if (conexaoExiste(origemId, destinoId) ) {
            throw new RuntimeException("Conexão já existe entre os usuários");
        }



        var conexao = new Conexao();

        conexao.setUsuarioOrigem(usuarioOrigem);

        conexao.setUsuarioDestino(usuarioDestino);

        conexao.setStatusConexao(StatusConexao.PENDENTE); // o outro usuário precisa aceitar a conexão

        var mensagem = usuarioOrigem.getNome() + " enviou uma solicitação de conexão para você.";

        // Salva primeiro para obter o id gerado pelo banco e então cria a notificação com esse id
        var conexaoSalva = conexaoRepository.save(conexao);

        notificacaoService.criarNotificacao(destinoId, mensagem, conexaoSalva.getId_conexao());
        return conexaoSalva;

    }

    @Transactional
    public void aceitarConexao(Long conexaoId, Long notificacaoId) {

        var conexao = conexaoRepository.findById(conexaoId)
                .orElseThrow(() -> new RuntimeException("Conexão não encontrada"));


        var usuarioOrigem = conexao.getUsuarioOrigem().getId_usuario();

        var usuarioDestino = conexao.getUsuarioDestino().getId_usuario();


        conexao.setStatusConexao(StatusConexao.ACEITA);

        notificacaoService.apagarNoficacao(notificacaoId);



        notificacaoService.notificarConexaoAceita(
                usuarioOrigem,
                usuarioDestino,
                conexao.getId_conexao()
        );


        conexaoRepository.save(conexao);
    }

    @Transactional
    public void recusarConexao(Long conexaoId, Long notificacaoId){
        var conexao = conexaoRepository.findById(conexaoId).orElseThrow(() -> new RuntimeException("Conexão não encontrada"));

        var usuarioOrigem = conexao.getUsuarioOrigem().getId_usuario();

        var usuarioDestino = conexao.getUsuarioDestino().getId_usuario();

        conexao.setStatusConexao(StatusConexao.RECUSADA);

        notificacaoService.apagarNoficacao(notificacaoId);

        conexaoRepository.save(conexao);

    }


    private Boolean conexaoExiste(Long origemId, Long destinoId) {
        return conexaoRepository.ligarConexoesPorUsuario(origemId)
                .stream()
                .anyMatch(c -> (c.getUsuarioDestino().getId_usuario().equals(destinoId) || c.getUsuarioOrigem().getId_usuario().equals(destinoId)));
    }

    private Boolean conexaoAceitaExiste(Long origemId, Long destinoId) {
        return conexaoRepository.ligarConexoesPorUsuario(origemId)
                .stream()
                .anyMatch(c -> (c.getUsuarioDestino().getId_usuario().equals(destinoId) || c.getUsuarioOrigem().getId_usuario().equals(destinoId)) && c.getStatusConexao() == StatusConexao.ACEITA);
    }

    public List<Conexao> listarConexoes(Long usuarioLogadoId) {

        return conexaoRepository.listarConexoesAceitasPorUsuarioDestino(usuarioLogadoId);

    }

    public List<ConexaoResponseDto> listarConexoesPorUsuario(Long usuarioId) {

        return conexaoRepository
                .ligarConexoesPorUsuario(usuarioId)
                .stream().map(ConexaoResponseDto::new).toList();
    }

    @Transactional
    public void excluirConexoesPorUsuario(Long idConexao) {
        conexaoRepository.deleteById(idConexao);
    }
}
