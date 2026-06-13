package com.projeto.soci.service;

import com.projeto.soci.dto.EventoRequestDto;
import com.projeto.soci.dto.EventoResponseDto;
import com.projeto.soci.model.Evento;
import com.projeto.soci.model.Usuario;
import com.projeto.soci.repository.EventoRepository;
import com.projeto.soci.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {


    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public EventoResponseDto criarEvento(
            Long usuarioId,
            EventoRequestDto dto) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        Evento evento = new Evento();

        evento.setTitulo(dto.titulo());
        evento.setDescricao(dto.descricao());
        evento.setData_evento(dto.dataEvento());
        evento.setLocal(dto.local());
        evento.setUsuario(usuario);

        Evento salvo = eventoRepository.save(evento);

        return new EventoResponseDto(salvo);
    }

    public List<EventoResponseDto> listarEventos() {

        return eventoRepository.findAll()
                .stream()
                .map(EventoResponseDto::new)
                .toList();
    }

    public EventoResponseDto buscarPorId(Long id) {

        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Evento não encontrado"));

        return new EventoResponseDto(evento);
    }

    public EventoResponseDto atualizarEvento(
            Long id,
            EventoRequestDto dto) {

        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Evento não encontrado"));

        evento.setTitulo(dto.titulo());
        evento.setDescricao(dto.descricao());
        evento.setData_evento(dto.dataEvento());
        evento.setLocal(dto.local());

        Evento atualizado = eventoRepository.save(evento);

        return new EventoResponseDto(atualizado);
    }

    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    @Transactional
    public void deletarEventosDoUsuario(Long usuarioId) {
        eventoRepository.deleteByUsuarioId(usuarioId);
    }
}
