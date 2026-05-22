package com.projeto.soci.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService {

    private final PasswordEncoder passwordEncoder;


    public EncoderService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String senha){
        return passwordEncoder.encode(senha);
    }

    public boolean matches(String senha, String senhaEncoded){
        return passwordEncoder.matches(senha, senhaEncoded);
    }


}
