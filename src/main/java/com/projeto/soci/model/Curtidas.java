package com.projeto.soci.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Curtidas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Curtidas {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_curtidas;
    @ManyToOne
    @JoinColumn(name = "publicacao_id", referencedColumnName = "id_publicacao")
    private Publicacao publicacao;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;

    private LocalDateTime dataCurtida;


}
