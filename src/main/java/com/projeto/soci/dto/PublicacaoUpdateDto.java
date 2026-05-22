package com.projeto.soci.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublicacaoUpdateDto(
        @NotBlank(message = "Conteúdo é obrigatório")
        @Size(max = 2000, message = "Conteúdo muito longo")
        String conteudo,

        @Size(max = 1024, message = "URL da imagem muito longa")
        String imagemUrl
) {
}
