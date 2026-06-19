package com.projeto.soci.dto.entrada;

import com.projeto.soci.enuns.TipoUsuario;
import jakarta.validation.constraints.*;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;

public record DadosCadastroUsuario(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        @Past(message = "Data de nascimento deve ser no passado")
        LocalDate dataNascimento,

        @NotBlank(message = "Senha é obrigatória")
        String senha,

        @Nullable
        String curso,
        @Nullable
        String fotoPerfil,
        @Nullable
        String biografia,

        @NotNull(message = "Tipo de usuário é obrigatório")
        TipoUsuario tipoUsuario,

        @Nullable
        String rgm
) {
}
