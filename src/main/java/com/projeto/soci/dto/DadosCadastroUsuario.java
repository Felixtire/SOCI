package com.projeto.soci.dto;

import com.projeto.soci.enuns.TipoUsuario;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DadosCadastroUsuario(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        @NotNull(message = "Data de nascimento é obrigatória")
        @Past(message = "Data de nascimento deve ser no passado")
        LocalDate dataNascimento,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve possuir ao menos 6 caracteres")
        String senha,

        String curso,
        String fotoPerfil,
        String biografia,

        @NotNull(message = "Tipo de usuário é obrigatório")
        TipoUsuario tipoUsuario,

        String rgm
) {
}
