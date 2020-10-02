package com.example.repository;

import com.example.model.Note;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface NoteRepository extends ReactiveMongoRepository<Note,String> {
    Flux<Note> deleteByNoteId(String noteId);

    Mono<Note> findByNoteId(String noteId);
}
