package com.projeto.soci.service;

import com.projeto.soci.dto.saida.NotificaoDtoSaida;
import com.projeto.soci.model.Notificacao;
import com.projeto.soci.repository.NotificacaoRepository;
import com.projeto.soci.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class NotificacaoService {

    private NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public void criarNotificacao(Long id, String mensagem) {

        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Notificacao notificacao = new Notificacao();
        notificacao.setUsuario(usuario);

        notificacao.setMensagem(mensagem);



        notificacaoRepository.save(notificacao);
    }

    public List<NotificaoDtoSaida> listarNotificacoesPorUsuario(Long id) {
        return notificacaoRepository.findByUsuarioId(id)
                .stream()
                .map(NotificaoDtoSaida::new)
                .toList();
    }



    public void apagarNoficacao(Long id) {
        var notificacao = notificacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        notificacaoRepository.delete(notificacao);

    }

    public void notificarConexaoAceita(Long usuarioDestinoId, Long usuarioOrigemId) {
        var usuarioOrigem = usuarioRepository.findById(usuarioOrigemId).orElseThrow(() -> new RuntimeException("Usuário de origem não encontrado"));

        var nomeUsuarioOrigem = usuarioOrigem.getNome();

        var mensagem = "Sua solicitação de conexão foi aceita por " + nomeUsuarioOrigem + ".";

        criarNotificacao(usuarioDestinoId, mensagem);
    }

}




