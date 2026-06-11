package com.projeto.soci.dto;

import com.projeto.soci.model.Conexao;
import com.projeto.soci.model.Usuario;

public record ConexaoResponseDto(
        Long conexaoId,
        Long usuarioOrigemId,
        String nomeUsuarioOrigem,
        Long usuarioDestinoId,
        String nomeUsuarioDestino,
        String fotoPerfilUsuarioOrigem,
        String fotoPerfilUsuarioDestino
) {
    public ConexaoResponseDto(Conexao resposta) {
        this(resposta.getId_conexao(), resposta.getUsuarioOrigem().getId_usuario(),  resposta.getUsuarioOrigem().getNome(),resposta.getUsuarioDestino().getId_usuario(), resposta.getUsuarioDestino().getNome(), resposta.getUsuarioOrigem().getFoto_perfil(), resposta.getUsuarioDestino().getFoto_perfil());
    }



}


