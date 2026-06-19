package com.projeto.soci.dto.saida;

import java.time.LocalDateTime;

public record PublicacaoDto(
        Long id,
        String conteudo,
        String imagemUrl,
        LocalDateTime dataPublicacao,
        Long usuarioId,
        String nomeUsuario
) {
}
