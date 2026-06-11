package com.projeto.soci.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Publicacao publicacao;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    private LocalDateTime dataCurtida;


}
