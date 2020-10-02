package com.example.service;

import com.example.dto.NoteDto;
import com.example.model.Note;
import reactor.core.publisher.Mono;

public interface INoteService {
    Mono<Note> save(NoteDto noteDto);
}
