package com.projeto.soci.model;

import com.projeto.soci.enuns.StatusConexao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Conexoes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conexao {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private  Long id_conexao;

    @ManyToOne
    @JoinColumn(name = "usuario_origem_id", referencedColumnName = "id_usuario")
    private Usuario usuarioOrigem;

    @ManyToOne
    @JoinColumn(name = "usuario_destino_id", referencedColumnName = "id_usuario")
    private Usuario usuarioDestino;

    @Enumerated(EnumType.STRING)
    private StatusConexao statusConexao;
}
