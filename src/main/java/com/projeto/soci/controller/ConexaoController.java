package com.projeto.soci.controller;

import com.projeto.soci.dto.saida.ConexaoResponseDto;
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

    @PutMapping("/{conexaoId}/aceitar")
    public ResponseEntity<?> aceitar(@PathVariable Long conexaoId){

        conexaoService.aceitarConexao(conexaoId);

        return ResponseEntity.ok("Conexão aceita");
    }

    @PutMapping("/{conexaoId}/recusar/{notificacaoId}")
    public ResponseEntity<?> recusar(@PathVariable Long conexaoId, @PathVariable Long notificacaoId) {
        conexaoService.recusarConexao(conexaoId, notificacaoId);

        return ResponseEntity.ok("Conexão recusada");
    }



    @GetMapping("/aceitas/{usuarioId}")
    public List<ConexaoResponseDto> listarConexoes(@PathVariable Long usuarioId) {
        return conexaoService.listarConexoes(usuarioId)
                .stream()
                .map(ConexaoResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{usuarioId}")
    public List<ConexaoResponseDto> listarConexoesUsuario(@PathVariable Long usuarioId) {
        return conexaoService.listarConexoesPorUsuario(usuarioId);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarConexao(@PathVariable Long id) {
        conexaoService.excluirConexoesPorUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
