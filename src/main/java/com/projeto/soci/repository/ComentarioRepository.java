package com.projeto.soci.repository;

import com.projeto.soci.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @Modifying
    @Query("delete from Comentario c where c.usuario.id_usuario = :userId or c.publicacao.usuario.id_usuario = :userId")
    void deleteByUsuarioOrPublicacaoUsuario(@Param("userId") Long userId);
}
