package com.projeto.soci.dto.saida;

import com.projeto.soci.model.Conexao;
import com.projeto.soci.model.Notificacao;
import com.projeto.soci.model.Usuario;
import jakarta.persistence.ManyToOne;

public record NotificaoDtoSaida(
        Long id_notificacao,

        String mensagem,

        Long idUsuarioDestino


) {
    public NotificaoDtoSaida(Notificacao resposta) {
        this(resposta.getId_notificacao(), resposta.getMensagem(), resposta.getUsuario().getId_usuario());
    }
}
