package com.projeto.soci.dto;

import java.time.LocalDateTime;

public record PublicacaoDto(
        Long id,
        String conteudo,
        String imagemUrl,
        LocalDateTime dataPublicacao,
        Long usuarioId
) {
}
