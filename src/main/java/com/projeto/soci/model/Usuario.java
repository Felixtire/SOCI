package com.projeto.soci.model;

import com.projeto.soci.dto.DadosCadastroUsuario;
import com.projeto.soci.enuns.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = "usuario")
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;

    private String nome;

    @Column(unique = true)
    private String email;

    private Date data_nascimento;

    private String senha;

    private String curso;

    private String foto_perfil;

    private String biografia;

    private Date data_criacao;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo_usuario;

    private String rgm;

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        if (dados.dataNascimento() != null) {
            this.data_nascimento = Date.from(dados.dataNascimento().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        this.senha = dados.senha();
        this.curso = dados.curso();
        this.foto_perfil = dados.fotoPerfil();
        this.biografia = dados.biografia();
        this.tipo_usuario = dados.tipoUsuario();
        this.rgm = dados.rgm();
        this.data_criacao = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


    @Override
    public @Nullable String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
