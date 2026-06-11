package com.projeto.soci.repository;

import com.projeto.soci.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    @Modifying
    @Query("delete from Publicacao p where p.usuario.id_usuario = :userId")
    void deleteByUsuarioId(@Param("userId") Long userId);
}
