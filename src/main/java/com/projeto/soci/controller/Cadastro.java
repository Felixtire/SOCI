package com.projeto.soci.controller;

import com.projeto.soci.dto.DadosCadastroUsuario;
import com.projeto.soci.dto.DadosCadastroUsuarioSaida;
import com.projeto.soci.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cadastro")
public class Cadastro {

    @Autowired
    private CadastroService cadastroService;


    @PostMapping
    public ResponseEntity<DadosCadastroUsuarioSaida> cadastrarUsuario(@Valid @RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
         var usuario = cadastroService.cadastrarUsuario(dados);

         URI uri = uriComponentsBuilder.path("/cadastro/{id}").buildAndExpand(usuario.id()).toUri();

         return ResponseEntity.created(uri).body(usuario);

    }

    @GetMapping
    public ResponseEntity<List<DadosCadastroUsuarioSaida>> listar() {
        return ResponseEntity.ok(cadastroService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCadastroUsuarioSaida> buscar(@PathVariable Long id) {
        var u = cadastroService.buscarPorId(id);
        if (u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosCadastroUsuarioSaida> atualizar(@PathVariable Long id, @Valid @RequestBody DadosCadastroUsuario dados) {
        var updated = cadastroService.atualizarUsuario(id, dados);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deleted = cadastroService.deletarUsuario(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

}
