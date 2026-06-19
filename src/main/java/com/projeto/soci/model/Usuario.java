package com.projeto.soci.model;

import com.projeto.soci.dto.entrada.DadosCadastroUsuario;
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
import java.util.ArrayList;
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
    @Column(name = "id_usuario")
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


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publicacao> publicacoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curtidas> curtidas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventos = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioOrigem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conexao> conexoesOrigem = new ArrayList<>();


    @OneToMany(mappedBy = "usuarioDestino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conexao> conexoesDestino = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacao> notificacoes = new ArrayList<>();

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


    public void addPublicacao(Publicacao p) {
        publicacoes.add(p);
        p.setUsuario(this);
    }

    public void removePublicacao(Publicacao p) {
        publicacoes.remove(p);
        p.setUsuario(null);
    }

    public void addComentario(Comentario c) {
        comentarios.add(c);
        c.setUsuario(this);
    }

    public void removeComentario(Comentario c) {
        comentarios.remove(c);
        c.setUsuario(null);
    }

    public void addCurtida(Curtidas cur) {
        curtidas.add(cur);
        cur.setUsuario(this);
    }

    public void removeCurtida(Curtidas cur) {
        curtidas.remove(cur);
        cur.setUsuario(null);
    }

    public void addEvento(Evento e) {
        eventos.add(e);
        e.setUsuario(this);
    }

    public void removeEvento(Evento e) {
        eventos.remove(e);
        e.setUsuario(null);
    }

    public void addConexaoOrigem(Conexao c) {
        conexoesOrigem.add(c);
        c.setUsuarioOrigem(this);
    }

    public void removeConexaoOrigem(Conexao c) {
        conexoesOrigem.remove(c);
        c.setUsuarioOrigem(null);
    }

    public void addConexaoDestino(Conexao c) {
        conexoesDestino.add(c);
        c.setUsuarioDestino(this);
    }

    public void removeConexaoDestino(Conexao c) {
        conexoesDestino.remove(c);
        c.setUsuarioDestino(null);
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
