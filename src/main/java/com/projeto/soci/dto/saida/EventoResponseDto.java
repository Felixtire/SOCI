package com.projeto.soci.dto.saida;

import com.projeto.soci.model.Evento;

import java.util.Date;

public record EventoResponseDto(
        Long idEvento,
        String titulo,
        String descricao,
        Date dataEvento,
        String local,
        Long usuarioId,
        String nomeUsuario )
{

    public EventoResponseDto(Evento evento) {
        this(
                evento.getId_evento(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getData_evento(),
                evento.getLocal(),
                evento.getUsuario().getId_usuario(),
                evento.getUsuario().getNome()
        );
    }


}
