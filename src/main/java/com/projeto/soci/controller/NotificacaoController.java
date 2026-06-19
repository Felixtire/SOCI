package com.projeto.soci.controller;
import com.projeto.soci.dto.saida.NotificaoDtoSaida;
import com.projeto.soci.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;


    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<NotificaoDtoSaida>> listarNotificacoes(
            @PathVariable Long usuarioId
    ){

        return ResponseEntity.ok(
                notificacaoService.listarNotificacoesPorUsuario(usuarioId)
        );

    }




}
