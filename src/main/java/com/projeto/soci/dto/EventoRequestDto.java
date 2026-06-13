package com.projeto.soci.dto;

import org.springframework.lang.Nullable;

import java.util.Date;

public record EventoRequestDto(
        String titulo,
        String descricao,
        Date dataEvento,

        @Nullable
        String local
) {
}