package com.projeto.soci.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;
}
