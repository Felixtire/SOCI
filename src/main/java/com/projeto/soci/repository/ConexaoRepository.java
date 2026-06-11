package com.projeto.soci.repository;

import com.projeto.soci.model.Conexao;
import com.projeto.soci.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    @Modifying
    @Query("delete from Conexao c where c.usuarioOrigem.id_usuario = :userId or c.usuarioDestino.id_usuario = :userId")
    void deleteByUsuarioId(@Param("userId") Long userId);
}
