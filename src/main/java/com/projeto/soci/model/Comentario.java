package com.projeto.soci.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comentarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comentario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comentario;

    private String texto;

    private LocalDateTime data_comentario;

    @ManyToOne
    @JoinColumn(name = "id_publicacao", referencedColumnName = "id_publicacao")
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}
