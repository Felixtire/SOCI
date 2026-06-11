package com.projeto.soci.repository;

import com.projeto.soci.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Modifying
    @Query("delete from Evento e where e.usuario.id_usuario = :userId")
    void deleteByUsuarioId(@Param("userId") Long userId);
}
