package com.projeto.soci.mapper;

import com.projeto.soci.dto.PublicacaoCreateDto;
import com.projeto.soci.dto.PublicacaoDto;
import com.projeto.soci.dto.PublicacaoUpdateDto;
import com.projeto.soci.model.Publicacao;
import com.projeto.soci.model.Usuario;

import java.time.LocalDateTime;

public class PublicacaoMapper {

    public static Publicacao toEntity(PublicacaoCreateDto dto, Usuario usuario) {
        Publicacao p = new Publicacao();
        p.setConteudo(dto.conteudo());
        p.setImagemUrl(dto.imagemUrl());
        p.setDataPublicacao(LocalDateTime.now());
        p.setUsuario(usuario);
        return p;
    }

    public static PublicacaoDto toDto(Publicacao p) {
        return new PublicacaoDto(
                p.getId_publicacao(),
                p.getConteudo(),
                p.getImagemUrl(),
                p.getDataPublicacao(),
                p.getUsuario() != null ? p.getUsuario().getId_usuario() : null,
                p.getUsuario().getNome()
        );
    }

    public static void updateEntity(Publicacao p, PublicacaoUpdateDto dto) {
        p.setConteudo(dto.conteudo());
        p.setImagemUrl(dto.imagemUrl());
    }
}
