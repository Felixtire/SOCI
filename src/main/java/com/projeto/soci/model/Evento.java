package com.projeto.soci.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Eventos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;

    private String titulo;

    private String descricao;

    private Date data_evento;

    private String local;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;


}
