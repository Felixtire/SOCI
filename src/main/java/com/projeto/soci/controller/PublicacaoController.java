package com.projeto.soci.controller;

import com.projeto.soci.dto.PublicacaoCreateDto;
import com.projeto.soci.dto.PublicacaoDto;
import com.projeto.soci.dto.PublicacaoUpdateDto;
import com.projeto.soci.service.PublicacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoService service;



    @GetMapping
    public List<PublicacaoDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PublicacaoDto buscarPorId(@PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<PublicacaoDto> criar(@Valid @RequestBody PublicacaoCreateDto dto) {
        var criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/publicacoes/" + criado.id())).body(criado);
    }

    @PutMapping("/{id}")
    public PublicacaoDto atualizar(@PathVariable Long id, @Valid @RequestBody PublicacaoUpdateDto dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

