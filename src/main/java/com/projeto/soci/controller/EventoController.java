package com.projeto.soci.controller;


import com.projeto.soci.dto.entrada.EventoRequestDto;
import com.projeto.soci.dto.saida.EventoResponseDto;
import com.projeto.soci.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {


    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<EventoResponseDto> criarEvento(
            @PathVariable Long usuarioId,
            @RequestBody EventoRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventoService.criarEvento(usuarioId, dto));
    }

    @GetMapping
    public List<EventoResponseDto> listarEventos() {
        return eventoService.listarEventos();
    }

    @GetMapping("/{id}")
    public EventoResponseDto buscarPorId(
            @PathVariable Long id) {

        return eventoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public EventoResponseDto atualizarEvento(
            @PathVariable Long id,
            @RequestBody EventoRequestDto dto) {

        return eventoService.atualizarEvento(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(
            @PathVariable Long id) {

        eventoService.deletarEvento(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> deletarEventosUsuario(
            @PathVariable Long usuarioId) {

        eventoService.deletarEventosDoUsuario(usuarioId);

        return ResponseEntity.noContent().build();
    }
}
