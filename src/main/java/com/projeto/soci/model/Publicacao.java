package com.projeto.soci.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Publicacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publicacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publicacao;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private String imagemUrl;

    private LocalDateTime dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curtidas> curtidas = new ArrayList<>();

    // Métodos auxiliares para manter ambos os lados sincronizados
    public void addComentario(Comentario c) {
        comentarios.add(c);
        c.setPublicacao(this);
    }

    public void removeComentario(Comentario c) {
        comentarios.remove(c);
        c.setPublicacao(null);
    }

    public void addCurtida(Curtidas cur) {
        curtidas.add(cur);
        cur.setPublicacao(this);
    }

    public void removeCurtida(Curtidas cur) {
        curtidas.remove(cur);
        cur.setPublicacao(null);
    }
}
