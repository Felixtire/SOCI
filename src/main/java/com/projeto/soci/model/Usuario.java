package com.projeto.soci.model;

import com.projeto.soci.dto.DadosCadastroUsuario;
import com.projeto.soci.enuns.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.util.Date;

@Entity(name = "usuario")
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;

    private String nome;

    private String email;

    private Date data_nascimento;

    private String senha;

    private String curso;

    private String foto_perfil;

    private String biografia;

    private Date data_criacao;

    private TipoUsuario tipo_usuario;

    private String rgm;

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        if (dados.dataNascimento() != null) {
            this.data_nascimento = Date.from(dados.dataNascimento().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        this.senha = dados.senha();
        this.curso = dados.curso();
        this.foto_perfil = dados.fotoPerfil();
        this.biografia = dados.biografia();
        this.tipo_usuario = dados.tipoUsuario();
        this.rgm = dados.rgm();
        this.data_criacao = new Date();
    }

}
