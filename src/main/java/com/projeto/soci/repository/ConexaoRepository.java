package com.projeto.soci.repository;

import com.projeto.soci.model.Conexao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConexaoRepository extends JpaRepository<Conexao,Long> {



    @Query("""
           SELECT c FROM Conexao c
           WHERE (
                c.usuarioOrigem.id_usuario = :usuarioId
                OR
                c.usuarioDestino.id_usuario = :usuarioId
           )
           """)
    List<Conexao> ligarConexoesPorUsuario(Long usuarioId);
}
