package com.projeto.soci.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Publicacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publicacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publicacao;

    private String conteudo;

    private String imagemUrl;

    private LocalDateTime dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;
}
