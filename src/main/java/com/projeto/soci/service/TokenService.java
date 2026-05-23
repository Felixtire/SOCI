package com.projeto.soci.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.projeto.soci.model.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private String secretkey="123456";


    public String gerarToken( Usuario usuario){

        try{
            Algorithm algoritmo = Algorithm.HMAC256(secretkey);

            return JWT.create()
                    .withIssuer("Soci_api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataLimite())
                    .withClaim("id", usuario.getId_usuario())
                    .sign(algoritmo);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token");
        }


    }


    private Instant dataLimite() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJwt){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secretkey);

            return JWT.require(algoritmo)
                    .withIssuer("Soci_api")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        }catch (Exception exception){
            throw new RuntimeException("Token inválido ou expirado");
        }
    }

}
