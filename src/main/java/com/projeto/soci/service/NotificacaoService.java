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
    public void criarNotificacao(
            Long id,
            String mensagem,
            Long idConexaoOriginaria
    ) {

        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        Notificacao notificacao = new Notificacao();

        notificacao.setUsuario(usuario);

        notificacao.setMensagem(mensagem);

        notificacao.setIdConexaoOriginaria(idConexaoOriginaria);


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

    public void notificarConexaoAceita(
            Long usuarioDestinoId,
            Long usuarioOrigemId,
            Long idConexaoOriginaria
    ) {

        var usuarioAceitou = usuarioRepository.findById(usuarioOrigemId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        String mensagem = usuarioAceitou.getNome()
                + " aceitou sua solicitação de conexão.";


        criarNotificacao(
                usuarioDestinoId,
                mensagem,
                idConexaoOriginaria
        );
    }

}




