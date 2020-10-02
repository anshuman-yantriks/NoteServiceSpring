package com.example.service;

import com.example.dto.NoteDto;
import com.example.model.Note;
import io.jsonwebtoken.Header;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface INoteService {
    Mono<Note> save(NoteDto noteDto, String token);

    Mono<Note> update(NoteDto noteDto, String token);

    Flux<Note> getNotes(String token);
}
