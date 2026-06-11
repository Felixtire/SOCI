package com.projeto.soci.service;

import com.projeto.soci.dto.PublicacaoCreateDto;
import com.projeto.soci.dto.PublicacaoDto;
import com.projeto.soci.dto.PublicacaoUpdateDto;
import com.projeto.soci.mapper.PublicacaoMapper;
import com.projeto.soci.model.Publicacao;
import com.projeto.soci.model.Usuario;
import com.projeto.soci.repository.PublicacaoRepository;
import com.projeto.soci.repository.UsuarioRepository;
import com.projeto.soci.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PublicacaoDto> listar() {
        return publicacaoRepository.findAll().stream().map(PublicacaoMapper::toDto).collect(Collectors.toList());
    }

    public PublicacaoDto buscarPorId(Long id) {
        return publicacaoRepository.findById(id).map(PublicacaoMapper::toDto).orElseThrow(() -> new NotFoundException("Publicacao não encontrada"));
    }

    @Transactional
    public PublicacaoDto criar(PublicacaoCreateDto dto) {

        Usuario usuarioLogado = (Usuario)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        Publicacao p = PublicacaoMapper.toEntity(dto, usuarioLogado);

        publicacaoRepository.save(p);

        return PublicacaoMapper.toDto(p);
    }
    @Transactional
    public PublicacaoDto atualizar(Long id, PublicacaoUpdateDto dto) {
        Publicacao p = publicacaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Publicacao não encontrada"));
        PublicacaoMapper.updateEntity(p, dto);
        publicacaoRepository.save(p);
        return PublicacaoMapper.toDto(p);
    }

    @Transactional
    public void deletar(Long id) {
        Publicacao p = publicacaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Publicacao não encontrada"));
        publicacaoRepository.delete(p);
    }
}

