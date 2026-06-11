package com.projeto.soci.service;

import com.projeto.soci.dto.DadosLogin;
import com.projeto.soci.dto.RespostaLoginDto;
import com.projeto.soci.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService service;
    @Autowired
    private EncoderService encode;



    @Transactional
    public RespostaLoginDto logar(DadosLogin login) {
        var usuarioOpt = repository.findByEmail(login.email());

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Login ou senha inválidos"
            );
        }

        var usuario = usuarioOpt.get();

        if (!encode.matches(login.senha(), usuario.getSenha())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Login ou senha inválidos"
            );
        }
        var id = usuario.getId_usuario();
        var token = service.gerarToken(usuario);

        return new RespostaLoginDto(id,token);

    }


}
