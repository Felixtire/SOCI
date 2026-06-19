package com.projeto.soci.dto.saida;

import com.projeto.soci.model.Usuario;

public record DadosCadastroUsuarioSaida(Long id, String nome, String email) {

    public DadosCadastroUsuarioSaida(Usuario u) {
        this(u.getId_usuario(), u.getNome(), u.getEmail());
    }
}

