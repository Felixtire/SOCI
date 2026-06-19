package com.projeto.soci.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notificacoes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notificacao;

    private String mensagem;

    private String nomeUsuarioOriginario;

    @ManyToOne
    private Usuario usuario;



}
