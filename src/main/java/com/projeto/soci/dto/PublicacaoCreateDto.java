package com.projeto.soci.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PublicacaoCreateDto(
        @NotBlank(message = "Conteúdo é obrigatório")
        @Size(max = 2000, message = "Conteúdo muito longo")
        String conteudo,

        @Size(max = 1024, message = "URL da imagem muito longa")
        String imagemUrl,

        @NotNull(message = "Id do usuário é obrigatório")
        Long usuarioId
) {
}
