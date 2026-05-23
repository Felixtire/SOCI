package com.projeto.soci.controller;


import com.projeto.soci.dto.DadosLogin;
import com.projeto.soci.repository.UsuarioRepository;
import com.projeto.soci.service.LoginService;
import com.projeto.soci.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid DadosLogin dados) {

        var userLogado= service.logar(dados);

        return ResponseEntity.ok((userLogado));
    }


}
