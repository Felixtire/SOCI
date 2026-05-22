package com.projeto.soci.controller;

import com.projeto.soci.model.Conexao;
import com.projeto.soci.dto.ConexaoResponseDto;
import com.projeto.soci.service.ConexaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/conexoes")
public class ConexaoController {

    private final ConexaoService conexaoService;

    public ConexaoController(ConexaoService conexaoService) {
        this.conexaoService = conexaoService;
    }

    @PostMapping("/{origemId}/{destinoId}")
    public ResponseEntity<ConexaoResponseDto> criarConexao(@PathVariable Long origemId, @PathVariable Long destinoId) {
        var c = conexaoService.conectarUsuarios(origemId, destinoId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ConexaoResponseDto(c));
    }



    @GetMapping
    public List<ConexaoResponseDto> listarConexoes() {
        return conexaoService.listarConexoes()
                .stream()
                .map(ConexaoResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{usuarioId}")
    public List<ConexaoResponseDto> listarConexoesUsuario(@PathVariable Long usuarioId) {
        return conexaoService.listarConexoesPorUsuario(usuarioId);

    }
}
