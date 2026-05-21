package com.projeto.soci.model;

import com.projeto.soci.enuns.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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




}
