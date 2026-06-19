package com.projeto.soci.repository;

import com.projeto.soci.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Long> {

    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id_usuario = :id")
    List<Notificacao> findByUsuarioId(@Param("id") Long id);


}
