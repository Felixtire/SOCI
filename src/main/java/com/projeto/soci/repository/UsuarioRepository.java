package com.projeto.soci.repository;

import com.projeto.soci.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
